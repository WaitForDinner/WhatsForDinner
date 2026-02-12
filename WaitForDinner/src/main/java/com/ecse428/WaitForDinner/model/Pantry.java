/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.8072.d3fbfafbc modeling language!*/

package com.ecse428.WaitForDinner.model;
import java.util.*;
import jakarta.persistence.*;

// line 65 "../../../../../model.ump"
// line 104 "../../../../../model.ump"
@Entity
@Table(name = "pantries")
public class Pantry
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Pantry Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int pantryId;
  
  private String name;

  //Pantry Associations
  @ManyToMany(mappedBy = "pantries")
  private List<User> users;
  
  @ManyToMany
  @JoinTable(
    name = "pantry_ingredient",
    joinColumns = @JoinColumn(name = "pantry_id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id")
  )
  private List<Ingredient> ingredients;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Pantry(String aName, int aPantryId)
  {
    name = aName;
    pantryId = aPantryId;
    users = new ArrayList<User>();
    ingredients = new ArrayList<Ingredient>();
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

  public boolean setPantryId(int aPantryId)
  {
    boolean wasSet = false;
    pantryId = aPantryId;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getPantryId()
  {
    return pantryId;
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
  public Ingredient getIngredient(int index)
  {
    Ingredient aIngredient = ingredients.get(index);
    return aIngredient;
  }

  public List<Ingredient> getIngredients()
  {
    List<Ingredient> newIngredients = Collections.unmodifiableList(ingredients);
    return newIngredients;
  }

  public int numberOfIngredients()
  {
    int number = ingredients.size();
    return number;
  }

  public boolean hasIngredients()
  {
    boolean has = ingredients.size() > 0;
    return has;
  }

  public int indexOfIngredient(Ingredient aIngredient)
  {
    int index = ingredients.indexOf(aIngredient);
    return index;
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
    if (aUser.indexOfPantry(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aUser.addPantry(this);
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
    if (aUser.indexOfPantry(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aUser.removePantry(this);
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
  public static int minimumNumberOfIngredients()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addIngredient(Ingredient aIngredient)
  {
    boolean wasAdded = false;
    if (ingredients.contains(aIngredient)) { return false; }
    ingredients.add(aIngredient);
    if (aIngredient.indexOfPantry(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aIngredient.addPantry(this);
      if (!wasAdded)
      {
        ingredients.remove(aIngredient);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeIngredient(Ingredient aIngredient)
  {
    boolean wasRemoved = false;
    if (!ingredients.contains(aIngredient))
    {
      return wasRemoved;
    }

    int oldIndex = ingredients.indexOf(aIngredient);
    ingredients.remove(oldIndex);
    if (aIngredient.indexOfPantry(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aIngredient.removePantry(this);
      if (!wasRemoved)
      {
        ingredients.add(oldIndex,aIngredient);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addIngredientAt(Ingredient aIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addIngredient(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveIngredientAt(Ingredient aIngredient, int index)
  {
    boolean wasAdded = false;
    if(ingredients.contains(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addIngredientAt(aIngredient, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<User> copyOfUsers = new ArrayList<User>(users);
    users.clear();
    for(User aUser : copyOfUsers)
    {
      aUser.removePantry(this);
    }
    ArrayList<Ingredient> copyOfIngredients = new ArrayList<Ingredient>(ingredients);
    ingredients.clear();
    for(Ingredient aIngredient : copyOfIngredients)
    {
      aIngredient.removePantry(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "pantryId" + ":" + getPantryId()+ "]";
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 68 ../../../../../model.ump
  // int ownerId - commented out as it's not a valid field declaration
  
}