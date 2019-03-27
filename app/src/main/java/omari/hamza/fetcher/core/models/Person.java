package omari.hamza.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

//The Person could be trainee or an employee
public class Person {


    @SerializedName("person_id")
    private int personId;

    private String name = "HAMZA";

    @SerializedName("employment_id")
    private int employeeId;

    @SerializedName("training_id")
    private int traineeId;

    @SerializedName("field_of_work")
    private String fieldOfWork;

    private boolean isTrainee = false;

    public boolean isTrainee() {
        return isTrainee;
    }

    public void setTrainee(boolean trainee) {
        isTrainee = trainee;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    public String getFieldOfWork() {
        return fieldOfWork;
    }

    public void setFieldOfWork(String fieldOfWork) {
        this.fieldOfWork = fieldOfWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
