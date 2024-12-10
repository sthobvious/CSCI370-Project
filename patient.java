public class patient {
    
    private int ID; //1,...,374
    private int gender; //0,1
    private int age; 
    private String occupation; //0,...,10
    private double sleepDuration;
    private int qualityOfSleep;
    private int physicalActivity;
    private int stressLevel;
    private int bmiCat;
    private int bloodPressure;
    private int heartRate;
    private int dailySteps; // 
    private int sleepDisorder; //-1,0,1

    public patient(){ //empty constructor
        this.ID=-1;
        this.gender=-1;
        this.age=-1;
        this.occupation=null;
        this.sleepDuration=-1.0;
        this.qualityOfSleep=-1;
        this.physicalActivity=-1;
        this.stressLevel=-1;
        this.bmiCat=-1;
        this.bloodPressure=-1;
        this.heartRate=-1;
        this.dailySteps=-1;
        this.sleepDisorder=-1;
    }

    patient(patient p){ //constructor to add new patients

    }

    public void printPatient(patient patient){
        System.out.println("patient id: " + patient.ID);
        System.out.println("patient gender: " + patient.gender);
        System.out.println("patient age: " + patient.age);
        System.out.println("patient occupation: " + patient.gender);
        System.out.println("patient's sleep duration: " + patient.sleepDuration);
        System.out.println("patient's quality of sleep: " + patient.sleepDuration);
        System.out.println("patient's physical activity: " + patient.physicalActivity);
        System.out.println("patient's stress level: " + patient.stressLevel);
        System.out.println("patient BMI category: " + patient.bmiCat);
        System.out.println("patient's blood pressure: " + patient.bloodPressure);
        System.out.println("patient's heart rate: " + patient.heartRate);
        System.out.println("patient's daily steps: " + patient.dailySteps);
        System.out.println("patient diagnosis status: " + patient.sleepDisorder);
    }

      // getter for ID
    public int getID() {
        return ID;
    }   
    // setter for ID
    public void setID(int newID) {
        this.ID = newID;
    }
    
    // getter for gender
    public int getGender() {
        return gender;
    }
    // setter for gender
    public void setGender(int newGender) {
        this.gender = newGender;
    }

    // getter for age
    public int getAge() {
        return age;
    }
    // setter for age
    public void setAge(int newAge) {
        this.age = newAge;
    }

    // getter for occupation
    public String getOccupation() {
        return occupation;
    }
    // setter for occupation
    public void setOccupation(String newOccupation) {
        this.occupation = newOccupation;
    }

    // getter for sleepDuration
    public double getsleepDuration() {
        return sleepDuration;
    }
    // setter for sleepDuration
    public void setsleepDuration(double newsleepDuration) {
        this.sleepDuration = newsleepDuration;
    }

    // getter for QoS
    public int getQualityOfSleep() {
        return qualityOfSleep;
    }
    // setter for QoS
    public void setQualityOfSleep(int qualityOfSleep) {
        this.qualityOfSleep = qualityOfSleep;
    }

    // getter for physicalActivity
    public int getphysicalActivity() {
        return physicalActivity;
    }
    // setter for physicalActivity
    public void setphysicalActivity(int newphysicalActivity) {
        this.physicalActivity = newphysicalActivity;
    }

    // getter for stressLevel
    public int getstressLevel() {
        return stressLevel;
    }
    // setter for stressLevel
    public void setstressLevel(int newstressLevel) {
        this.stressLevel = newstressLevel;
    }

    // getter for BMI
    public int getbmiCat() {
        return bmiCat;
    }
    // setter for BMI
    public void setbmiCat(int newbmiCat) {
        this.bmiCat = newbmiCat;
    }

    // getter for bloodPressure
    public int getbloodPressure() {
        return bloodPressure;
    }
    // setter for bloodPressure
    public void setbloodPressure(int newbloodPressure) {
        this.bloodPressure = newbloodPressure;
    }

    // getter for heartRate
    public int getheartRate() {
        return heartRate;
    }
    // setter for heartRate
    public void setheartRate(int newheartRate) {
        this.heartRate = newheartRate;
    }

    // getter for dailySteps
    public int getdailySteps() {
        return dailySteps;
    }
    // setter for dailySteps
    public void setdailySteps(int newdailySteps) {
        this.dailySteps = newdailySteps;
    }

    // getter for sleepDisorder
    public int getsleepDisorder() {
        return sleepDisorder;
    }
    // setter for sleepDisorder
    public void setsleepDisorder(int newsleepDisorder) {
        this.sleepDisorder = newsleepDisorder;
    }

}
