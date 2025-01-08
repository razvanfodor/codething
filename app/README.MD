

**Scenario**

Imagine you're developing a program for a fitness tracker app. 

The app tracks various activities like running, cycling, and swimming. 

Each _activity_ has its way of calculating _calories_ burned, but they all share some common functionalities like recording _duration_ and _distance_.

**Task**
1. Add a Running activity and show calories burnt
2. Add a Swimming activity and show calories burnt
3. Add a Walking activity and show calories burnt
4. Add the ability to include BMR (Base metabolic rate - i.e. latent calories burnt regardless of activity)
5. Demonstrate how this influences Running or another activity

Bonus points: support multiple users or other features that make sense


**Solution**
BMR ist the amount of calories that a person needs to keep the body functioning at rest. 
BMR does not influence and is not influenced by any user activity.
I took the design decision to calculate BMR separately from the activity calories.
It is calculated at the user level, as it is a user specific property.

BMR is taken into account when calculating the total calories burnt by a user in a day.

In order to see the results, please run Main.java