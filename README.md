# AI Chat Assistant 🤖

A modern, responsive Android application built with **Kotlin** and **Jetpack Compose**. This project demonstrates a clean implementation of an AI-powered conversational interface using modern Android development practices.

---

## 📸 Preview

| Chat Interface | Dark Mode | Dynamic Theming |
|:---:|:---:|:---:|
| <img width="413" height="807" alt="Screenshot 2026-02-23 194950" src="https://github.com/user-attachments/assets/93054247-f3c4-42ac-b2da-6cfda614edb4" /> | <img width="399" height="809" alt="Screenshot 2026-02-23 195108" src="https://github.com/user-attachments/assets/d502c209-ae95-4b58-9a25-98980f94184a" /> | <img width="410" height="799" alt="Screenshot 2026-02-23 195058" src="https://github.com/user-attachments/assets/1062882d-288b-40ac-a52a-30324a612837" /> |

| <p align="center">**Sharing Intent**</p> | <p align="center">**Chat Preview**</p> | |
|:---:|:---:|:---:|
| <img width="411" height="787" alt="Screenshot 2026-02-23 195040" src="https://github.com/user-attachments/assets/fdf800b2-b278-46cf-8a48-38880add17f7" /> | <img width="388" height="787" alt="Screenshot 2026-02-23 195027" src="https://github.com/user-attachments/assets/9b0def35-4d2e-4997-9ca2-837bd73a73c3" /> | |

---

## 🛠 Tech Stack & Tools

* **Language:** [Kotlin](https://kotlinlang.org/) - 100% Type-safe and idiomatic.
* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - Declarative UI development.
* **Design System:** **Material 3 (M3)** - Implementing modern Google Design guidelines.
* **Local Database:** **Room** - Robust local caching and data persistence.
* **Image Loading:** **Coil** - Kotlin-first, lightweight image loading library.
* **Networking:** Retrofit & OkHttp - Handling API requests to the AI backend.
* **Architecture:** **MVVM (Model-View-ViewModel)** - Ensuring separation of concerns and testability.
* **Concurrency:** Kotlin Coroutines & Flow - For smooth, non-blocking UI and API calls.

## ✨ Key Features & Technical Implementation

* **Advanced Material 3 UI:** Leveraged M3 components and **Custom Theming** (ColorSchemes, Typography, and Shapes) to provide a premium look and feel.
* **Offline-First Support:** Integrated **Room Database** to cache chat history locally, allowing users to view previous conversations without an internet connection.
* **Asynchronous AI Interaction:** Used **Coroutines** to handle long-running AI processing without freezing the UI thread.
* **Dynamic State Management:** Used `State` and `MutableState` within the ViewModel to handle different UI states: *Idle, Loading, Success, and Error*.
* **Optimized Lists:** Efficiently rendering chat bubbles using `LazyColumn` for maximum performance.
* **Image Handling:** Integrated **Coil** to handle user avatars and media within the chat fluently.

---

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/NavneetSindhu/ChatBot.git](https://github.com/NavneetSindhu/ChatBot.git)
