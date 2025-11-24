[![Java CI with Maven](https://github.com/chris-cgsit/train_2025_java_aufbau_bsp/actions/workflows/maven.yml/badge.svg)](https://github.com/chris-cgsit/train_2025_java_aufbau_bsp/actions/workflows/maven.yml)
[![License: CC BY-NC 4.0](https://img.shields.io/badge/License-CC%20BY--NC%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-nc/4.0/)



# Java Maven Projekt – Case-Insensitive Suche

## Überblick 

Dieses Projekt dient als Beispielimplementierung für eine Case-Insensitive Suche nach Personendaten in einer Java-Anwendung. Es demonstriert zwei Implementierungsansätze:

1. **Stream-basierte Suche** (Modern, deklarativ)

2. **Iterator-basierte Suche** (Klassisch, imperativ)

Die verwendete Implementierung kann zur Laufzeit über eine Umgebungsvariable gesteuert werden (`FIRMA_IMPL=S` oder `FIRMA_IMPL=I`).

## CI/CD Status (Build & Tests)

Der Status der Continuous Integration (CI) wird direkt von GitHub Actions abgerufen.

| Badge | Status | 
| ----- | ----- | 
| **Build Status** |  | 
| **Test Coverage** | *Dieser Badge muss von einem Coverage-Tool wie JaCoCo oder Codecov bereitgestellt werden.* | 

> **Wichtig:** Sie müssen die Platzhalter `{Link_zum_Build_Badge}` und `{Link_zum_Workflow_Run}` durch die tatsächlichen Links aus Ihrem GitHub-Repository ersetzen.

### So erhalten Sie den Build Status Badge:

1. Gehen Sie in Ihrem GitHub Repository zum Tab **Actions**.

2. Klicken Sie auf den Workflow-Namen (z.B. `Java Maven CI`).

3. Klicken Sie oben rechts auf **"Create status badge"** (oder **"Status-Badge erstellen"**).

4. Kopieren Sie den Markdown-Code und fügen Sie ihn hier ein.

## Technologie-Stack

* **Sprache:** Java (Basierend auf JDK 23)

* **Build-Tool:** Maven

* **Testing:** JUnit 5 (Version 5.10.0)

* **CI:** GitHub Actions

## Lokale Ausführung

### Voraussetzungen

Stellen Sie sicher, dass Java Development Kit (JDK) **Version 23** oder neuer auf Ihrem System installiert ist.

### Kompilieren und Testen

Verwenden Sie Maven, um das Projekt zu bauen und alle Unit-Tests auszuführen:

mvn clean install


### Ausführung mit Implementierungs-Auswahl

Sie können die Implementierung der Suchlogik über die Umgebungsvariable `FIRMA_IMPL` steuern:

| Umgebungsvariable | Implementierung | 
| ----- | ----- | 
| `FIRMA_IMPL=S` (Default) | Stream-basierte Suche | 
| `FIRMA_IMPL=I` | Iterator-basierte Suche | 

**Beispiel (Linux/macOS): Stream-Implementierung verwenden**

siehe Buch Tipps:
[CGS Buchtips](https://cgs.at/book-suggestions/)
[CGS Hardware tips](https://cgs.at/cgs-it-amazon-affiliate-link)
[CGS IT Solutions](https://cgs.at/)



