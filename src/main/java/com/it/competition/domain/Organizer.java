package com.it.competition.domain;

public class Organizer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organizer.id
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organizer.name
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organizer.user_name
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organizer.password
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organizer.id
     *
     * @return the value of organizer.id
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organizer.id
     *
     * @param id the value for organizer.id
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organizer.name
     *
     * @return the value of organizer.name
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organizer.name
     *
     * @param name the value for organizer.name
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organizer.user_name
     *
     * @return the value of organizer.user_name
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organizer.user_name
     *
     * @param userName the value for organizer.user_name
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organizer.password
     *
     * @return the value of organizer.password
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organizer.password
     *
     * @param password the value for organizer.password
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organizer
     *
     * @mbg.generated Fri Apr 26 18:38:50 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }
}