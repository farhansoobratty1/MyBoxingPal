# MyBoxingPal

A desktop training log for boxers, written in Java with Swing and JFreeChart. I built it for a client, an amateur boxer who wanted a better way to track his training.

He logs boxing sessions (rounds completed, punch success rate, notes) and gym sessions (body weight, workout type, duration). The app shows his progress as charts and keeps a full session history he can export and send to his coach.

## The brief

The client was logging his workouts and sparring in an Excel spreadsheet. That was slow to fill in after a tiring session, gave him no feedback, made trends hard to see, and was awkward to share with his coach. He asked for:

- quick, simple data entry for boxing metrics (punch success rate, rounds) and gym sessions, usable while tired after training
- charts and graphs so he could compare his current performance with earlier sessions at a glance
- a way to export his data to share with his coach

Before starting, I compared the existing apps with him:

- **MyFitnessPal** and **Strava** have nothing boxing-specific, such as rounds or punch success rate.
- **Trainerize** supports coaches but has a more complex interface than he wanted.

We agreed on a custom desktop app in Java, for its object-oriented structure, Swing for the interface and JFreeChart for the graphs. Before development started, he signed off a list of requirements covering login, the dashboard, logging, charts and history.

## How the project ran

**April–August 2025. Uploaded to GitHub in November 2026.**
The dates on this repository show when I uploaded it, not when I did the work.

| When | Stage |
|---|---|
| Late April 2025 | First consultation: went through his current spreadsheet and what wasn't working |
| May 2025 | Proposed the solution, researched alternatives, agreed on the tech stack and signed off the requirements |
| May–July 2025 | Design (page-by-page versions, structure chart, data flow diagram, test plan) and development |
| Mid-July 2025 | Delivered and installed on his laptop, and walked him through setup and login |
| Late July 2025 | Follow-up review: every agreed requirement was confirmed as met |
| August 2025 | He sent requests for future features (see below) |

At the review he said he could "easily enter my username and password", that the buttons were "intuitive and quick to navigate", and that the history table was "much easier to read than the raw CSV".

## Features

- **Accounts:** a welcome screen with sign-up and login. Each user's training data is stored in its own file.
- **Dashboard:** a personalised welcome message, buttons for each section, and the date and time of your last logged session.
- **Boxing log:** punch success rate, rounds completed and notes. The number fields only accept numbers.
- **Gym log:** body weight, workout type (Cardio, Strength, Core, Flexibility or HIIT, picked from a dropdown) and duration.
- **Save prompts:** the app asks before saving, confirms when a session is saved, and warns you about unsaved data if you leave a log page.
- **Progress charts** (JFreeChart), each filtered by 1 week, 1 month, 3 months, 1 year or all time:
  - a line graph of body weight over time
  - a bar chart of rounds completed
  - a pie chart showing how your workouts are split by type
- **Session history:** a table of every session you've logged, with an **Export** button that saves your training data as a CSV file.

## Running it

You need **Java 21 or newer**. JFreeChart is included in `lib/`.

**In Eclipse:** go to *File → Import → Existing Projects into Workspace*, select this folder, then run `myboxpal.WelcomePage`.

**From a terminal**, in this folder:

```
javac -cp lib/jfreechart.jar -d bin src/myboxpal/*.java
java -cp "bin;lib/jfreechart.jar" myboxpal.WelcomePage
```

(On macOS or Linux, use `:` instead of `;` in the second command.)

Run it from the project folder, because the app reads its images and data files from the folder it's started in.

**Demo accounts:** log in as `demo` / `demo123` or `alex` / `alex123`. Both have some sample sessions, so the charts and history have something to show. You can also sign up with a new account.

## How it's put together

| Path | What it is |
|---|---|
| `src/myboxpal/WelcomePage.java` | Entry point: choose Login or Sign Up. |
| `LoginPage.java`, `SignupPage.java`, `Login.java` | Authentication. Users are read from `users.txt` into a `LinkedList` of `Login` objects. |
| `Dashboard.java` | The main menu after logging in. |
| `LogWorkoutPage.java`, `BoxingLogPage.java`, `GymLogPage.java` | Logging sessions, which are appended to `<username>_trainingData.csv`. |
| `ViewProgressPage.java`, `WeightDataPage.java`, `RoundCompletionDataPage.java`, `WorkoutDistributionPage.java` | The JFreeChart progress charts. The pie chart totals each workout type with a `HashMap`. |
| `SessionHistoryPage.java` | The history table (`JTable`) and CSV export. |
| `Session.java` | A class that holds one training session's data. |
| `users.txt`, `*_trainingData.csv` | Demo data. The client's real logins and training data aren't included. |

Things I practised: Swing GUI design, reading, searching and writing text and CSV files, encapsulation, inheritance, linked lists and hash maps, and building charts with JFreeChart.

Each line of a training-data CSV is:
`date, workout type, body weight, rounds completed, punch success rate, duration (min), notes`

## Limitations and ideas for the future

- **Passwords are stored in plain text** in `users.txt`. That's fine for a local demo but not for real use. The next step would be hashing them, for example with PBKDF2 or bcrypt.
- Commas in the notes field break the CSV columns, because each line is split on commas.
- After delivery, the client asked for two new features, which I've planned but not built yet:
  - **goal setting**, e.g. "punch success rate above 80% by a target date", with a popup as he gets close
  - **PDF progress reports** that include the charts, to send to his coach

## Credits

- [JFreeChart](https://www.jfree.org/jfreechart/) by David Gilbert and contributors, LGPL. It's included in `lib/`.
