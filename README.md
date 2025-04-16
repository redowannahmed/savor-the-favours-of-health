# Savor the Favours of Health

**Savor the Favours of Health** is a modular, SOLID-compliant Java console-based application that helps users improve and monitor their overall well-being. It offers AI-assisted health queries, sleep analysis, daily goal tracking, nutritional insights, and mood logging — all in a single interactive console interface.

> 🧠 Live better. Sleep deeper. Eat smarter. Feel more.

## 📌 Table of Contents

- [Features](#features)
- [Project Architecture](#project-architecture)
- [Technologies Used](#technologies-used)
- [How to Run](#how-to-run)
- [Folder Structure](#folder-structure)
- [Justification & Thematic Relevance](#justification--thematic-relevance)

---

## 🌟 Features

### 1. ✅ Daily Health Goals
- Add, update, and track daily health-related goals.
- Status markers: `Pending`, `In Progress`, `Done`.
- Console table rendering for easy visualization.

### 2. 💤 Sleep Pattern Analyzer
- Log sleep sessions and receive AI-generated analysis.
- Provides sleep tips and progress feedback using visual bars.

### 3. 🤖 AI Health Query
- Ask personalized health-related questions.
- Integrates with free Mistral AI (mistral-tiny) for instant responses.

### 4. 😊 Daily Mood Logger
- Rate daily moods, write personal notes, and track trends.
- View historical data for emotional self-awareness.

### 5. 🍎 Nutrition Tracker
- Retrieve nutrition facts from a local JSON food database.
- Displays calories, vitamins, and mineral data in an informative layout.

---

## 🧱 Project Architecture

### 🧩 Modular Design & Factory Pattern
- Each feature is isolated via factory-generated modules for scalability.
- Factories include: `AIHealthQueryFactory`, `MoodTrackingFactory`, `SleepDataAnalysisFactory`, etc.

### 🧠 SOLID Principles Applied
- **Single Responsibility**: Clear separation between UI, logic, and persistence.
- **Open/Closed**: Easily extendable via interfaces and factories.
- **Liskov & Interface Segregation**: Feature-specific abstractions like `iSleepAnalyzer`, `iMoodService`, etc.
- **Dependency Inversion**: All logic depends on abstractions, not concrete classes.

### 📦 Layered Structure
- **UI Layer**: Interactive console UIs (`MoodTrackerUI`, `SleepAnalysisUI`, etc.)
- **Logic Layer**: Controllers and services (`GoalServiceImpl`, `MoodServiceImpl`)
- **Data Layer**: Repositories using `.txt` and `.json` files (e.g., `TxtGoalRepository`, `JSONNutritionRepository`)

---

## 🛠️ Technologies Used

- **Language**: Java
- **IDE**: Visual Studio Code
- **Data Storage**: Text & JSON files
- **JSON Parsing**: [Gson 2.10.1](https://github.com/google/gson)
- **AI Integration**: [Mistral AI API](https://docs.mistral.ai/) — `mistral-tiny` model

---

## 🚀 How to Run

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/savor-health.git
   cd savor-health
