package myboxpal;

public class Session {
	// Encapsulation
    private String date; // Private variable storing the session date
    private String workoutType; // Private variable storing the type of workout
    private Float bodyWeight; // Private variable storing body weight in kg
    private Integer roundsCompleted; // Private variable storing rounds completed in boxing
    private Float punchSuccessRate; // Private variable storing punch success rate percentage
    private Integer durationMinutes; // Private variable storing workout duration in minutes
    private String notes; // Private variable storing any additional notes
    
    public Session(String date, String workoutType, Float bodyWeight,
                   Integer roundsCompleted, Float punchSuccessRate,
                   Integer durationMinutes, String notes) { // Public constructor with all parameters
        this.date = date; // Assigns date parameter to private field
        this.workoutType = workoutType; // Assigns workoutType parameter to private field
        this.bodyWeight = bodyWeight; // Assigns bodyWeight parameter to private field
        this.roundsCompleted = roundsCompleted; // Assigns roundsCompleted parameter to private field
        this.punchSuccessRate = punchSuccessRate; // Assigns punchSuccessRate parameter to private field
        this.durationMinutes = durationMinutes; // Assigns durationMinutes parameter to private field
        this.notes = notes; // Assigns notes parameter to private field
    }
    
    public String getDate() { return date; } // Public getter to retrieve date
    public String getWorkoutType() { return workoutType; } // Public getter to retrieve workout type
    public Float getBodyWeight() { return bodyWeight; } // Public getter to retrieve body weight
    public Integer getRoundsCompleted() { return roundsCompleted; } // Public getter to retrieve rounds completed
    public Float getPunchSuccessRate() { return punchSuccessRate; } // Public getter to retrieve punch success rate
    public Integer getDurationMinutes() { return durationMinutes; } // Public getter to retrieve duration
    public String getNotes() { return notes; } // Public getter to retrieve notes
}
