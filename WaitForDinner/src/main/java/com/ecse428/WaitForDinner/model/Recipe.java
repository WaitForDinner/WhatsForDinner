/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.8072.d3fbfafbc modeling language!*/

package com.ecse428.WaitForDinner.model;
import java.util.*;
import jakarta.persistence.*;

// line 37 "../../../../../model.ump"
// line 89 "../../../../../model.ump"
@Entity
@Table(name = "recipes")
public class Recipe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Recipe Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int recipeId;
  
  private String name;
  
  @Column(columnDefinition = "TEXT")
  private String steps;
  
  private String difficulty;
  
  private int time;
  
  private int portions;

  //Recipe Associations
  @ManyToMany
  @JoinTable(
    name = "recipe_folder",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "folder_id")
  )
  private List<Folder> folders;
  
  @ManyToMany
  @JoinTable(
    name = "recipe_ingredient",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id")
  )
  private List<Ingredient> ingredients;
  
  @ManyToMany
  @JoinTable(
    name = "recipe_diet_type",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "diet_type_id")
  )
  private List<DietType> dietTypes;
  
  @ManyToMany
  @JoinTable(
    name = "recipe_preference_type",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "preference_type_id")
  )
  private List<PreferenceType> preferenceTypes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Recipe(String aName, String aSteps, String aDifficulty, int aTime, int aPortions, int aRecipeId)
  {
    name = aName;
    steps = aSteps;
    difficulty = aDifficulty;
    time = aTime;
    portions = aPortions;
    recipeId = aRecipeId;
    folders = new ArrayList<Folder>();
    ingredients = new ArrayList<Ingredient>();
    dietTypes = new ArrayList<DietType>();
    preferenceTypes = new ArrayList<PreferenceType>();
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

  public boolean setSteps(String aSteps)
  {
    boolean wasSet = false;
    steps = aSteps;
    wasSet = true;
    return wasSet;
  }

  public boolean setDifficulty(String aDifficulty)
  {
    boolean wasSet = false;
    difficulty = aDifficulty;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(int aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPortions(int aPortions)
  {
    boolean wasSet = false;
    portions = aPortions;
    wasSet = true;
    return wasSet;
  }

  public boolean setRecipeId(int aRecipeId)
  {
    boolean wasSet = false;
    recipeId = aRecipeId;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getSteps()
  {
    return steps;
  }

  public String getDifficulty()
  {
    return difficulty;
  }

  public int getTime()
  {
    return time;
  }

  public int getPortions()
  {
    return portions;
  }

  public int getRecipeId()
  {
    return recipeId;
  }
  /* Code from template association_GetMany */
  public Folder getFolder(int index)
  {
    Folder aFolder = folders.get(index);
    return aFolder;
  }

  public List<Folder> getFolders()
  {
    List<Folder> newFolders = Collections.unmodifiableList(folders);
    return newFolders;
  }

  public int numberOfFolders()
  {
    int number = folders.size();
    return number;
  }

  public boolean hasFolders()
  {
    boolean has = folders.size() > 0;
    return has;
  }

  public int indexOfFolder(Folder aFolder)
  {
    int index = folders.indexOf(aFolder);
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
  /* Code from template association_GetMany */
  public DietType getDietType(int index)
  {
    DietType aDietType = dietTypes.get(index);
    return aDietType;
  }

  public List<DietType> getDietTypes()
  {
    List<DietType> newDietTypes = Collections.unmodifiableList(dietTypes);
    return newDietTypes;
  }

  public int numberOfDietTypes()
  {
    int number = dietTypes.size();
    return number;
  }

  public boolean hasDietTypes()
  {
    boolean has = dietTypes.size() > 0;
    return has;
  }

  public int indexOfDietType(DietType aDietType)
  {
    int index = dietTypes.indexOf(aDietType);
    return index;
  }
  /* Code from template association_GetMany */
  public PreferenceType getPreferenceType(int index)
  {
    PreferenceType aPreferenceType = preferenceTypes.get(index);
    return aPreferenceType;
  }

  public List<PreferenceType> getPreferenceTypes()
  {
    List<PreferenceType> newPreferenceTypes = Collections.unmodifiableList(preferenceTypes);
    return newPreferenceTypes;
  }

  public int numberOfPreferenceTypes()
  {
    int number = preferenceTypes.size();
    return number;
  }

  public boolean hasPreferenceTypes()
  {
    boolean has = preferenceTypes.size() > 0;
    return has;
  }

  public int indexOfPreferenceType(PreferenceType aPreferenceType)
  {
    int index = preferenceTypes.indexOf(aPreferenceType);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFolders()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addFolder(Folder aFolder)
  {
    boolean wasAdded = false;
    if (folders.contains(aFolder)) { return false; }
    folders.add(aFolder);
    if (aFolder.indexOfRecipe(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aFolder.addRecipe(this);
      if (!wasAdded)
      {
        folders.remove(aFolder);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeFolder(Folder aFolder)
  {
    boolean wasRemoved = false;
    if (!folders.contains(aFolder))
    {
      return wasRemoved;
    }

    int oldIndex = folders.indexOf(aFolder);
    folders.remove(oldIndex);
    if (aFolder.indexOfRecipe(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aFolder.removeRecipe(this);
      if (!wasRemoved)
      {
        folders.add(oldIndex,aFolder);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFolderAt(Folder aFolder, int index)
  {  
    boolean wasAdded = false;
    if(addFolder(aFolder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFolders()) { index = numberOfFolders() - 1; }
      folders.remove(aFolder);
      folders.add(index, aFolder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFolderAt(Folder aFolder, int index)
  {
    boolean wasAdded = false;
    if(folders.contains(aFolder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFolders()) { index = numberOfFolders() - 1; }
      folders.remove(aFolder);
      folders.add(index, aFolder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFolderAt(aFolder, index);
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
    if (aIngredient.indexOfRecipe(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aIngredient.addRecipe(this);
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
    if (aIngredient.indexOfRecipe(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aIngredient.removeRecipe(this);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDietTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addDietType(DietType aDietType)
  {
    boolean wasAdded = false;
    if (dietTypes.contains(aDietType)) { return false; }
    dietTypes.add(aDietType);
    if (aDietType.indexOfRecipe(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aDietType.addRecipe(this);
      if (!wasAdded)
      {
        dietTypes.remove(aDietType);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeDietType(DietType aDietType)
  {
    boolean wasRemoved = false;
    if (!dietTypes.contains(aDietType))
    {
      return wasRemoved;
    }

    int oldIndex = dietTypes.indexOf(aDietType);
    dietTypes.remove(oldIndex);
    if (aDietType.indexOfRecipe(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aDietType.removeRecipe(this);
      if (!wasRemoved)
      {
        dietTypes.add(oldIndex,aDietType);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDietTypeAt(DietType aDietType, int index)
  {  
    boolean wasAdded = false;
    if(addDietType(aDietType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDietTypes()) { index = numberOfDietTypes() - 1; }
      dietTypes.remove(aDietType);
      dietTypes.add(index, aDietType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDietTypeAt(DietType aDietType, int index)
  {
    boolean wasAdded = false;
    if(dietTypes.contains(aDietType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDietTypes()) { index = numberOfDietTypes() - 1; }
      dietTypes.remove(aDietType);
      dietTypes.add(index, aDietType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDietTypeAt(aDietType, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPreferenceTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPreferenceType(PreferenceType aPreferenceType)
  {
    boolean wasAdded = false;
    if (preferenceTypes.contains(aPreferenceType)) { return false; }
    preferenceTypes.add(aPreferenceType);
    if (aPreferenceType.indexOfRecipe(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPreferenceType.addRecipe(this);
      if (!wasAdded)
      {
        preferenceTypes.remove(aPreferenceType);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePreferenceType(PreferenceType aPreferenceType)
  {
    boolean wasRemoved = false;
    if (!preferenceTypes.contains(aPreferenceType))
    {
      return wasRemoved;
    }

    int oldIndex = preferenceTypes.indexOf(aPreferenceType);
    preferenceTypes.remove(oldIndex);
    if (aPreferenceType.indexOfRecipe(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPreferenceType.removeRecipe(this);
      if (!wasRemoved)
      {
        preferenceTypes.add(oldIndex,aPreferenceType);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPreferenceTypeAt(PreferenceType aPreferenceType, int index)
  {  
    boolean wasAdded = false;
    if(addPreferenceType(aPreferenceType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPreferenceTypes()) { index = numberOfPreferenceTypes() - 1; }
      preferenceTypes.remove(aPreferenceType);
      preferenceTypes.add(index, aPreferenceType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePreferenceTypeAt(PreferenceType aPreferenceType, int index)
  {
    boolean wasAdded = false;
    if(preferenceTypes.contains(aPreferenceType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPreferenceTypes()) { index = numberOfPreferenceTypes() - 1; }
      preferenceTypes.remove(aPreferenceType);
      preferenceTypes.add(index, aPreferenceType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPreferenceTypeAt(aPreferenceType, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Folder> copyOfFolders = new ArrayList<Folder>(folders);
    folders.clear();
    for(Folder aFolder : copyOfFolders)
    {
      aFolder.removeRecipe(this);
    }
    ArrayList<Ingredient> copyOfIngredients = new ArrayList<Ingredient>(ingredients);
    ingredients.clear();
    for(Ingredient aIngredient : copyOfIngredients)
    {
      aIngredient.removeRecipe(this);
    }
    ArrayList<DietType> copyOfDietTypes = new ArrayList<DietType>(dietTypes);
    dietTypes.clear();
    for(DietType aDietType : copyOfDietTypes)
    {
      aDietType.removeRecipe(this);
    }
    ArrayList<PreferenceType> copyOfPreferenceTypes = new ArrayList<PreferenceType>(preferenceTypes);
    preferenceTypes.clear();
    for(PreferenceType aPreferenceType : copyOfPreferenceTypes)
    {
      aPreferenceType.removeRecipe(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "steps" + ":" + getSteps()+ "," +
            "difficulty" + ":" + getDifficulty()+ "," +
            "time" + ":" + getTime()+ "," +
            "portions" + ":" + getPortions()+ "," +
            "recipeId" + ":" + getRecipeId()+ "]";
  }
}