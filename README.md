# Textursynthese mit Image Quilting

Eine Java-Anwendung zur Erzeugung großflächiger, nahtloser Texturen mithilfe des Image-Quilting-Algorithmus.

## Über das Projekt

Aus einer kleinen Bildvorlage wird eine großflächige und nahtlose Textur erzeugt. Grundlage ist der Image-Quilting-Algorithmus (Efros & Freeman, 2001), bei dem kleine Bildbereiche systematisch ausgeschnitten und so zusammengesetzt werden, dass ein stimmiges Gesamtbild ohne sichtbare Wiederholungen entsteht.

## Funktionalitäten

- Eingabebild laden
- Patchgröße über einen Schieberegler einstellen
- Textur per Knopfdruck generieren
- Ergebnis als Bilddatei speichern

## Projektstruktur

```
TextureQuiltingTool/
├── src/
│   ├── Main.java                # Einstiegspunkt der Anwendung
│   ├── TextureQuiltLogic.java   # Kernlogik (Algorithmus, Bildverarbeitung)
│   └── TextureQuiltUI.java      # Grafische Benutzeroberfläche
└── Images/                      # Beispielbilder
```

## Technologien

- Java (Swing für die GUI)

## Starten der Anwendung

```bash
javac src/*.java
java -cp src Main
```

## Literatur

Efros, A.A., & Freeman, W.T. (2001). *Image Quilting for Texture Synthesis and Transfer*. SIGGRAPH Proceedings, 341–346.
