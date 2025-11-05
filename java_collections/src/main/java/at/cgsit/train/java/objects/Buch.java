package at.cgsit.train.java.objects;

import java.util.Objects;

public class Buch {
  private final Integer id;
  private String name;

  public Buch(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Buch)) return false;
    Buch buch = (Buch) o;
    return Objects.equals(id, buch.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Buch{id='%s', name='%s'}".formatted(id, name);
  }
}
