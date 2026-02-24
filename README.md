# CSCI370 — Sleep Health Classifier

A Java desktop application that uses a from-scratch **Random Forest** machine learning model to predict sleep health outcomes based on patient lifestyle data. Built as a course project for CSCI370.

---

## Overview

This project trains a Random Forest classifier on the [Sleep Health and Lifestyle Dataset](Sleep_health_and_lifestyle_dataset.csv) and provides a GUI-driven patient management system. Users can add, edit, and remove patient records, fill out a lifestyle survey, and receive a sleep disorder prediction powered by the trained model.

---

## Features

- **Random Forest classifier** implemented from scratch (no ML libraries)
- **Decision tree** and **node** classes forming the foundation of the ensemble model
- **Swing-based GUI** for full patient record management
- **Survey interface** to collect patient lifestyle inputs
- **CSV dataset** integration for model training

---

## Project Structure

| File | Description |
|---|---|
| `mainProgram.java` | Application entry point |
| `mainGUI.java` | Main application window |
| `randomForest.java` | Random Forest ensemble model |
| `decisionTree.java` | Individual decision tree implementation |
| `node.java` | Tree node structure |
| `patient.java` | Patient data model |
| `patientForm.java` | Patient form logic |
| `addPatientGUI.java` | GUI for adding a new patient |
| `editPatientGUI.java` | GUI for editing an existing patient |
| `removePatientGUI.java` | GUI for removing a patient |
| `surveyGUI.java` | Lifestyle survey interface for predictions |
| `Sleep_health_and_lifestyle_dataset.csv` | Training dataset |

---

## Requirements

- **Java 11+**
- No external dependencies — the standard Java library and Swing are all that's needed

---

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/sthobvious/CSCI370-Project.git
   cd CSCI370-Project
   ```

2. **Compile all Java files**
   ```bash
   javac *.java
   ```

3. **Run the application**
   ```bash
   java mainProgram
   ```

---

## Dataset

The model is trained on the **Sleep Health and Lifestyle Dataset**, which contains records with features such as:

- Age, gender, and occupation
- Sleep duration and quality
- Physical activity level
- BMI and heart rate
- Daily steps
- Blood pressure
- Sleep disorder label (None / Sleep Apnea / Insomnia)

---

## License

This project is licensed under the [BSD 2-Clause License](LICENSE).
