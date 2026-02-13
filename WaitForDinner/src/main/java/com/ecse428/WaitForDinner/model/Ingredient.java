/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.36.0.8091.03bcab5b3 modeling language!*/

package com.ecse428.WaitForDinner.model;
import java.util.*;

import jakarta.persistence.*;

// line 45 "../../../../../model.ump"
// line 99 "../../../../../model.ump"
@Entity
@Table(name = "ingredients")
public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private String name;
  private int shelfLife;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ingredientId;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "ingredient_histories",
      joinColumns = @JoinColumn(name = "ingredient_id"),
      inverseJoinColumns = @JoinColumn(name = "history_id"))
  private List<History> histories;

  @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
  private List<User> users;

  @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Need> needs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aName, int aShelfLife, int aIngredientId)
  {
    name = aName;
    shelfLife = aShelfLife;
    ingredientId = aIngredientId;
    histories = new ArrayList<History>();
    users = new ArrayList<User>();
    needs = new ArrayList<Need>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setShelfLife(int aShelfLife)
  {
    boolean wasSet = false;
    shelfLife = aShelfLife;
    wasSet = true;
    return wasSet;
  }

  public boolean setIngredientId(int aIngredientId)
  {
    boolean wasSet = false;
    ingredientId = aIngredientId;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getShelfLife()
  {
    return shelfLife;
  }

  public int getIngredientId()
  {
    return ingredientId;
  }
  /* Code from template association_GetMany */
  public History getHistory(int index)
  {
    History aHistory = histories.get(index);
    return aHistory;
  }

  public List<History> getHistories()
  {
    List<History> newHistories = Collections.unmodifiableList(histories);
    return newHistories;
  }

  public int numberOfHistories()
  {
    int number = histories.size();
    return number;
  }

  public boolean hasHistories()
  {
    boolean has = histories.size() > 0;
    return has;
  }

  public int indexOfHistory(History aHistory)
  {
    int index = histories.indexOf(aHistory);
    return index;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
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
  /* Code from template association_GetMany */
  public Need getNeed(int index)
  {
    Need aNeed = needs.get(index);
    return aNeed;
  }

  public List<Need> getNeeds()
  {
    List<Need> newNeeds = Collections.unmodifiableList(needs);
    return newNeeds;
  }

  public int numberOfNeeds()
  {
    int number = needs.size();
    return number;
  }

  public boolean hasNeeds()
  {
    boolean has = needs.size() > 0;
    return has;
  }

  public int indexOfNeed(Need aNeed)
  {
    int index = needs.indexOf(aNeed);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHistories()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addHistory(History aHistory)
  {
    boolean wasAdded = false;
    if (histories.contains(aHistory)) { return false; }
    histories.add(aHistory);
    if (aHistory.indexOfIngredient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aHistory.addIngredient(this);
      if (!wasAdded)
      {
        histories.remove(aHistory);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeHistory(History aHistory)
  {
    boolean wasRemoved = false;
    if (!histories.contains(aHistory))
    {
      return wasRemoved;
    }

    int oldIndex = histories.indexOf(aHistory);
    histories.remove(oldIndex);
    if (aHistory.indexOfIngredient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aHistory.removeIngredient(this);
      if (!wasRemoved)
      {
        histories.add(oldIndex,aHistory);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHistoryAt(History aHistory, int index)
  {  
    boolean wasAdded = false;
    if(addHistory(aHistory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHistories()) { index = numberOfHistories() - 1; }
      histories.remove(aHistory);
      histories.add(index, aHistory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHistoryAt(History aHistory, int index)
  {
    boolean wasAdded = false;
    if(histories.contains(aHistory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHistories()) { index = numberOfHistories() - 1; }
      histories.remove(aHistory);
      histories.add(index, aHistory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHistoryAt(aHistory, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    users.add(aUser);
    if (aUser.indexOfIngredient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aUser.addIngredient(this);
      if (!wasAdded)
      {
        users.remove(aUser);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    if (!users.contains(aUser))
    {
      return wasRemoved;
    }

    int oldIndex = users.indexOf(aUser);
    users.remove(oldIndex);
    if (aUser.indexOfIngredient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aUser.removeIngredient(this);
      if (!wasRemoved)
      {
        users.add(oldIndex,aUser);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNeeds()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Need addNeed(int aAmount, String aModifier, int aNeedId, Recipe aRecipe)
  {
    return new Need(aAmount, aModifier, aNeedId, aRecipe, this);
  }

  public boolean addNeed(Need aNeed)
  {
    boolean wasAdded = false;
    if (needs.contains(aNeed)) { return false; }
    Ingredient existingIngredient = aNeed.getIngredient();
    boolean isNewIngredient = existingIngredient != null && !this.equals(existingIngredient);
    if (isNewIngredient)
    {
      aNeed.setIngredient(this);
    }
    else
    {
      needs.add(aNeed);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeNeed(Need aNeed)
  {
    boolean wasRemoved = false;
    //Unable to remove aNeed, as it must always have a ingredient
    if (!this.equals(aNeed.getIngredient()))
    {
      needs.remove(aNeed);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addNeedAt(Need aNeed, int index)
  {  
    boolean wasAdded = false;
    if(addNeed(aNeed))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNeeds()) { index = numberOfNeeds() - 1; }
      needs.remove(aNeed);
      needs.add(index, aNeed);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNeedAt(Need aNeed, int index)
  {
    boolean wasAdded = false;
    if(needs.contains(aNeed))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNeeds()) { index = numberOfNeeds() - 1; }
      needs.remove(aNeed);
      needs.add(index, aNeed);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNeedAt(aNeed, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<History> copyOfHistories = new ArrayList<History>(histories);
    histories.clear();
    for(History aHistory : copyOfHistories)
    {
      aHistory.removeIngredient(this);
    }
    ArrayList<User> copyOfUsers = new ArrayList<User>(users);
    users.clear();
    for(User aUser : copyOfUsers)
    {
      aUser.removeIngredient(this);
    }
    for(int i=needs.size(); i > 0; i--)
    {
      Need aNeed = needs.get(i - 1);
      aNeed.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "shelfLife" + ":" + getShelfLife()+ "," +
            "ingredientId" + ":" + getIngredientId()+ "]";
  }
}