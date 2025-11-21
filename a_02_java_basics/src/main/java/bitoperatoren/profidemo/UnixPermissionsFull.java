package bitoperatoren.profidemo;

public class UnixPermissionsFull {

    // ===== Dateityp (Bits 15–12) =====
    // 	0b Dies ist das Präfix für binäre Literale in Java. Es signalisiert dem Compiler,
    // 	  dass die folgende Zahl im Dualsystem (Basis 2) interpretiert werden soll.
    // 	Dies sind sogenannte Zifferntrenner (Digit Separators), ebenfalls ab Java 7 verfügbar.
    //	Sie haben keine mathematische Funktion, sondern dienen ausschließlich der besseren Lesbarkeit für Menschen. Sie können sie überall zwischen Ziffern verwenden.

    static final int TYPE_MASK  = 0b1111_0000_0000_0000;
    static final int TYPE_REG   = 0b0001_0000_0000_0000; // -  regular file
    static final int TYPE_DIR   = 0b0010_0000_0000_0000; // d  directory
    static final int TYPE_SYML  = 0b0011_0000_0000_0000; // l  symlink
    static final int TYPE_CHAR  = 0b0100_0000_0000_0000; // c  char device
    static final int TYPE_BLOCK = 0b0101_0000_0000_0000; // b  block device
    static final int TYPE_FIFO  = 0b0110_0000_0000_0000; // p  pipe
    static final int TYPE_SOCK  = 0b0111_0000_0000_0000; // s  socket

    // ===== Spezialbits (Bits 11–9) =====
    static final int SUID   = 1 << 11;   // setuid
    static final int SGID   = 1 << 10;   // setgid
    static final int STICKY = 1 << 9;    // sticky bit

    // ===== Permissions rwx (Bits 8–0) =====
    // Owner
    static final int UR = 1 << 8;
    static final int UW = 1 << 7;
    static final int UX = 1 << 6;

    // Group
    static final int GR = 1 << 5;
    static final int GW = 1 << 4;
    static final int GX = 1 << 3;

    // Others
    static final int OR = 1 << 2;
    static final int OW = 1 << 1;
    static final int OX = 1 << 0;

    public static void main(String[] args) {
        int mode1 = 0;
        mode1 = set(mode1, TYPE_REG);
        mode1 = set(mode1, UR | UW | UX | GR | GX | OR | OX); // -rwxr-xr-x

        int mode2 = 0;
        mode2 = set(mode2, TYPE_DIR);
        mode2 = set(mode2, UR | UW | UX | GR | GX | OR | OX); // drwxr-xr-x
        mode2 = set(mode2, SUID | SGID | STICKY);             // + Spezialbits

        System.out.println("=== mode1 ===");
        dumpMode(mode1);

        System.out.println("\n=== mode2 ===");
        dumpMode(mode2);
    }

    // ===== Bit-Hilfsfunktionen =====

    static int set(int mode, int flags) {
        return mode | flags;
    }

    static int clear(int mode, int flags) {
        return mode & ~flags;
    }

    static boolean has(int mode, int flag) {
        return (mode & flag) != 0;
    }

    // ===== Darstellung =====

    static void dumpMode(int mode) {
        System.out.println("Raw (hex)   : 0x" + Integer.toHexString(mode & 0xFFFF));
        System.out.println("Symbolisch  : " + toSymbolic(mode));
        System.out.println("Oktal (chmod): " + toOctalWithSpecial(mode));
        System.out.println("Dateityp    : " + fileTypeName(mode));
        System.out.println("Bits (16)   : " + bits16(mode));
    }

    static String toSymbolic(int mode) {
        StringBuilder sb = new StringBuilder(10);

        // 1) Dateityp
        sb.append(fileTypeChar(mode));

        // 2) owner rwx, mit setuid (s/S)
        sb.append(has(mode, UR) ? 'r' : '-');
        sb.append(has(mode, UW) ? 'w' : '-');
        char ownerExec = has(mode, UX) ? 'x' : '-';
        if (has(mode, SUID)) {
            ownerExec = has(mode, UX) ? 's' : 'S';
        }
        sb.append(ownerExec);

        // 3) group rwx, mit setgid (s/S)
        sb.append(has(mode, GR) ? 'r' : '-');
        sb.append(has(mode, GW) ? 'w' : '-');
        char groupExec = has(mode, GX) ? 'x' : '-';
        if (has(mode, SGID)) {
            groupExec = has(mode, GX) ? 's' : 'S';
        }
        sb.append(groupExec);

        // 4) others rwx, mit sticky bit (t/T)
        sb.append(has(mode, OR) ? 'r' : '-');
        sb.append(has(mode, OW) ? 'w' : '-');
        char otherExec = has(mode, OX) ? 'x' : '-';
        if (has(mode, STICKY)) {
            otherExec = has(mode, OX) ? 't' : 'T';
        }
        sb.append(otherExec);

        return sb.toString();
    }

    // nur 4-stellige chmod-Notation: Spezialbits + rwx/rwx/rwx
    static String toOctalWithSpecial(int mode) {
        int special = (mode >> 9) & 0b111;  // suid/sgid/sticky
        int owner   = (mode >> 6) & 0b111;
        int group   = (mode >> 3) & 0b111;
        int others  = (mode      ) & 0b111;
        return "" + special + owner + group + others;
    }

    static char fileTypeChar(int mode) {
        int t = mode & TYPE_MASK;
        return switch (t) {
            case TYPE_REG   -> '-';
            case TYPE_DIR   -> 'd';
            case TYPE_SYML  -> 'l';
            case TYPE_CHAR  -> 'c';
            case TYPE_BLOCK -> 'b';
            case TYPE_FIFO  -> 'p';
            case TYPE_SOCK  -> 's';
            default         -> '?';
        };
    }

    static String fileTypeName(int mode) {
        int t = mode & TYPE_MASK;
        return switch (t) {
            case TYPE_REG   -> "regular file";
            case TYPE_DIR   -> "directory";
            case TYPE_SYML  -> "symbolic link";
            case TYPE_CHAR  -> "character device";
            case TYPE_BLOCK -> "block device";
            case TYPE_FIFO  -> "FIFO/pipe";
            case TYPE_SOCK  -> "socket";
            default         -> "unknown";
        };
    }

    static String bits16(int mode) {
        StringBuilder sb = new StringBuilder(19);
        for (int i = 15; i >= 0; i--) {
            sb.append(((mode >> i) & 1) == 1 ? '1' : '0');
            if (i % 4 == 0 && i != 0) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
