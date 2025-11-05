package at.cgsit.train.java.mv.personen;

import java.util.UUID;

/**
 * Abstrakte Basisklasse Person, repräsentiert allgemeine Personeninformationen.
 * Gemeinsam genutzt von Mitarbeiter und Kunde.
 */
public abstract class Person {
  /**
   * Eindeutige ID für jede Person, wird mithilfe von UUID generiert.
   */
  private final String id;

  /**
   * Vorname der Person.
   */
  private String vorname;

  /**
   * Nachname der Person.
   */
  private String nachname;

  /**
   * E-Mail-Adresse der Person.
   */
  private String email;

  // Konstruktor
  public Person(String vorname, String nachname, String email) {
    this.id = UUID.randomUUID().toString();
    this.vorname = vorname;
    this.nachname = nachname;
    this.email = email;
  }

  // Getter und Setter
  public String getId() {
    return id;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // Überschriebene Methode für die String-Darstellung
  @Override
  public String toString() {
    return String.format("ID: %s, Name: %s %s, Email: %s", id, vorname, nachname, email);
  }
}