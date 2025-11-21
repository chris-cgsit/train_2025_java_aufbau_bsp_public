
# Ablauf

1. **Server starten**

   ```bash
   java SimpleSocketServer
   ```

   → zeigt: `Server läuft auf Port 5000 ...`

2. **Client starten**

   ```bash
   java SimpleSocketClient
   ```

   → zeigt: `Antwort: Server sagt hallo zurück!`

3. **Serverausgabe:**

   ```
   Client verbunden: /127.0.0.1
   Empfangen: Hallo vom Client!
   ```

---

# **Lernpunkte:**

* `ServerSocket.accept()` blockiert, bis sich jemand verbindet
* Kommunikation über `InputStream` / `OutputStream`
* Alles reine Java-SE-Klassen, kein HTTP-Overhead* 
* * Sehr gut erweiterbar zu Chat-Broadcasts (mehr Clients + Threads)
