package com.ecse428.WaitForDinner.model;

import jakarta.persistence.*;

@Entity
@Table(name = "custom_restrictions")
public class CustomRestriction
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int customRestrictionId;

  @Column(nullable = false)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  protected CustomRestriction() {}

  public CustomRestriction(String aDescription, User aUser)
  {
    description = aDescription;
    user = aUser;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    user = aUser;
    wasSet = true;
    return wasSet;
  }

  public int getCustomRestrictionId()
  {
    return customRestrictionId;
  }

  public String getDescription()
  {
    return description;
  }

  public User getUser()
  {
    return user;
  }

  public String toString()
  {
    return super.toString() + "[" +
            "customRestrictionId" + ":" + getCustomRestrictionId() + "," +
            "description" + ":" + getDescription() + "]";
  }
}
