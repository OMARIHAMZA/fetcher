package omari.hamza.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Offer implements Serializable {

    @SerializedName("job_opportunity_id")
    private int jobId;

    @SerializedName("training_opportunity_id")
    private int trainingId;

    @SerializedName("company_id")
    private int companyId;

    @SerializedName("end_of_submission")
    private String deadline;

    @SerializedName("requirements")
    private String requirements;

    @SerializedName("salary")
    private String salary;

    @SerializedName("place")
    private String location;

    @SerializedName("days")
    private String days;

    @SerializedName("title")
    private String title;

    @SerializedName("name")
    private String companyName;

    @SerializedName("website")
    private String companyWebsite;

    @SerializedName("official_email")
    private String companyEmail;

    @SerializedName("main_address")
    private String companyLocation;

    private boolean isTraining;

    public Offer() {
    }

    public Offer(int id, int companyId, String deadline, String requirements, String salary, String location, String days, String title, String companyName, String companyWebsite, String companyEmail, String companyLocation, boolean isTraining) {
        this.jobId = id;
        this.companyId = companyId;
        this.deadline = deadline;
        this.requirements = requirements;
        this.salary = salary;
        this.location = location;
        this.days = days;
        this.title = title;
        this.companyName = companyName;
        this.companyWebsite = companyWebsite;
        this.companyEmail = companyEmail;
        this.companyLocation = companyLocation;
        this.isTraining = isTraining;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public boolean isTraining() {
        return isTraining;
    }

    public void setTraining(boolean training) {
        isTraining = training;
    }
}
