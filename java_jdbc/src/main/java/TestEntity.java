import java.time.Instant;

/**
 * CREATE TABLE test_entity (
 *     id IDENTITY PRIMARY KEY,
 *     name VARCHAR(200) NOT NULL,
 *     aktiv BOOLEAN,
 *     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 * );
 */
public class TestEntity {
    private Long id;
    private String name;
    private Boolean aktiv;
    private Instant createdAt;

    public TestEntity() {
        this.name = null;
        this.aktiv = null;
    }


    public TestEntity(String name, Boolean aktiv) {
        this.name = name;
        this.aktiv = aktiv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAktiv() {
        return aktiv;
    }

    public void setAktiv(Boolean aktiv) {
        this.aktiv = aktiv;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aktiv=" + aktiv +
                ", createdAt=" + createdAt +
                '}';
    }
}
