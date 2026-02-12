/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.8072.d3fbfafbc modeling language!*/

package com.ecse428.WaitForDinner.model;
import java.util.*;
import jakarta.persistence.*;

// line 56 "../../../../../model.ump"
// line 99 "../../../../../model.ump"
@Entity
@Table(name = "ingredients")
public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ingredientId;
  
  private String name;
  
  private String shelfLife;

  //Ingredient Associations
  @ManyToMany(mappedBy = "ingredients")
  private List<Pantry> pantries;
  
  @ManyToMany(mappedBy = "ingredients")
  private List<User> users;
  
  @ManyToMany(mappedBy = "ingredients")
  private List<Recipe> recipes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aName, String aShelfLife, int aIngredientId)
  {
    name = aName;
    shelfLife = aShelfLife;
    ingredientId = aIngredientId;
    pantries = new ArrayList<Pantry>();
    users = new ArrayList<User>();
    recipes = new ArrayList<Recipe>();
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

  public boolean setShelfLife(String aShelfLife)
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

  public String getShelfLife()
  {
    return shelfLife;
  }

  public int getIngredientId()
  {
    return ingredientId;
  }
  /* Code from template association_GetMany */
  public Pantry getPantry(int index)
  {
    Pantry aPantry = pantries.get(index);
    return aPantry;
  }

  public List<Pantry> getPantries()
  {
    List<Pantry> newPantries = Collections.unmodifiableList(pantries);
    return newPantries;
  }

  public int numberOfPantries()
  {
    int number = pantries.size();
    return number;
  }

  public boolean hasPantries()
  {
    boolean has = pantries.size() > 0;
    return has;
  }

  public int indexOfPantry(Pantry aPantry)
  {
    int index = pantries.indexOf(aPantry);
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
  public Recipe getRecipe(int index)
  {
    Recipe aRecipe = recipes.get(index);
    return aRecipe;
  }

  public List<Recipe> getRecipes()
  {
    List<Recipe> newRecipes = Collections.unmodifiableList(recipes);
    return newRecipes;
  }

  public int numberOfRecipes()
  {
    int number = recipes.size();
    return number;
  }

  public boolean hasRecipes()
  {
    boolean has = recipes.size() > 0;
    return has;
  }

  public int indexOfRecipe(Recipe aRecipe)
  {
    int index = recipes.indexOf(aRecipe);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPantries()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPantry(Pantry aPantry)
  {
    boolean wasAdded = false;
    if (pantries.contains(aPantry)) { return false; }
    pantries.add(aPantry);
    if (aPantry.indexOfIngredient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPantry.addIngredient(this);
      if (!wasAdded)
      {
        pantries.remove(aPantry);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePantry(Pantry aPantry)
  {
    boolean wasRemoved = false;
    if (!pantries.contains(aPantry))
    {
      return wasRemoved;
    }

    int oldIndex = pantries.indexOf(aPantry);
    pantries.remove(oldIndex);
    if (aPantry.indexOfIngredient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPantry.removeIngredient(this);
      if (!wasRemoved)
      {
        pantries.add(oldIndex,aPantry);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPantryAt(Pantry aPantry, int index)
  {  
    boolean wasAdded = false;
    if(addPantry(aPantry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPantries()) { index = numberOfPantries() - 1; }
      pantries.remove(aPantry);
      pantries.add(index, aPantry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePantryAt(Pantry aPantry, int index)
  {
    boolean wasAdded = false;
    if(pantries.contains(aPantry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPantries()) { index = numberOfPantries() - 1; }
      pantries.remove(aPantry);
      pantries.add(index, aPantry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPantryAt(aPantry, index);
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
  public static int minimumNumberOfRecipes()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRecipe(Recipe aRecipe)
  {
    boolean wasAdded = false;
    if (recipes.contains(aRecipe)) { return false; }
    recipes.add(aRecipe);
    if (aRecipe.indexOfIngredient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRecipe.addIngredient(this);
      if (!wasAdded)
      {
        recipes.remove(aRecipe);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeRecipe(Recipe aRecipe)
  {
    boolean wasRemoved = false;
    if (!recipes.contains(aRecipe))
    {
      return wasRemoved;
    }

    int oldIndex = recipes.indexOf(aRecipe);
    recipes.remove(oldIndex);
    if (aRecipe.indexOfIngredient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRecipe.removeIngredient(this);
      if (!wasRemoved)
      {
        recipes.add(oldIndex,aRecipe);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRecipeAt(Recipe aRecipe, int index)
  {  
    boolean wasAdded = false;
    if(addRecipe(aRecipe))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipes()) { index = numberOfRecipes() - 1; }
      recipes.remove(aRecipe);
      recipes.add(index, aRecipe);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRecipeAt(Recipe aRecipe, int index)
  {
    boolean wasAdded = false;
    if(recipes.contains(aRecipe))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipes()) { index = numberOfRecipes() - 1; }
      recipes.remove(aRecipe);
      recipes.add(index, aRecipe);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRecipeAt(aRecipe, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Pantry> copyOfPantries = new ArrayList<Pantry>(pantries);
    pantries.clear();
    for(Pantry aPantry : copyOfPantries)
    {
      aPantry.removeIngredient(this);
    }
    ArrayList<User> copyOfUsers = new ArrayList<User>(users);
    users.clear();
    for(User aUser : copyOfUsers)
    {
      aUser.removeIngredient(this);
    }
    ArrayList<Recipe> copyOfRecipes = new ArrayList<Recipe>(recipes);
    recipes.clear();
    for(Recipe aRecipe : copyOfRecipes)
    {
      aRecipe.removeIngredient(this);
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