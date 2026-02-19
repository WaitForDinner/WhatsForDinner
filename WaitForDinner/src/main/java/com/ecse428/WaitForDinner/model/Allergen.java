package com.ecse428.WaitForDinner.model;
import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "allergens")
public class Allergen
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int allergenId;

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany(mappedBy = "allergens", fetch = FetchType.LAZY)
  private List<User> users;

  protected Allergen()
  {
    users = new ArrayList<User>();
  }

  public Allergen(String aName)
  {
    name = aName;
    users = new ArrayList<User>();
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public int getAllergenId()
  {
    return allergenId;
  }

  public String getName()
  {
    return name;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    users.add(aUser);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    if (users.contains(aUser))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public String toString()
  {
    return super.toString() + "[" +
            "allergenId" + ":" + getAllergenId() + "," +
            "name" + ":" + getName() + "]";
  }
}
