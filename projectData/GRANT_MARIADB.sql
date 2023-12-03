/* Give Privileges to user  on database*/
GRANT ALL PRIVILEGES ON recipemanagement.* TO 'mydata'@'%';

/* Reload Privileges */
FLUSH PRIVILEGES;