# OrderSystem
## api for order system
![react-native-Order System ](https://github.com/jhuilin/storage/blob/master/orderSystem.gif)

# APIs
## Admin authorization:
### County:
		http://localhost:8080/admin/county/deleteById/{id}		(id is integer)
		http://localhost:8080/admin/county/deleteByName/{name}		(name is String)
		http://localhost:8080/admin/county/add
   ```json
   {
    "name": "county name" 
   }
   ```

	
### Type:
		http://localhost:8080/admin/type/deleteById/{id}			(id is integer)
		http://localhost:8080/admin/type/deleteByName/{name}		(name is String)
		http://localhost:8080/admin/type/add
   ```json
   {
    "name": "type name" 
   }
   ```

### Unit:
		http://localhost:8080/admin/unit/deleteById/{id}			(id is integer)
		http://localhost:8080/admin/unit/deleteByName/{name}		(name is String)
		http://localhost:8080/admin/unit/add
   ```json
   {
    "name": "unit name" 
   }
   ```
	
### Admin:
		http://localhost:8080/admin/store/update	(change admin password)
   ```json
   {
    "username": "username",
    "password": "new password"
   }
   ```

### Store:
		http://localhost:8080/admin/store/delete/{id}	(id is integer) delete store by store id



## Store authorization:
### product:
		http://localhost:8080/secure/product/add	(add new product)  
  ```json
  {
    "name": "product name",
    "qty": "product qty",
    "price": "20.1",
    "unit": "unit name",
    "type": "type name",
    "store": "store name",
  }
```

http://localhost:8080/secure/product/update	(update existing product)
  ```json
  {
    "pid": "pid",
    "name": "new product name",
    "qty": "new qty",
    "price": "new price",
    "state": "new state",
    "unit": "new unit",
    "type": "new type",
    "store": "store name"
  }
```
		
http://localhost:8080/secure/product/deleteById/{id}

### Store:
		http://localhost:8080/secure/store/update	update store info by username
  ```json
  {
    "store": "store id",
    "storeName": "new store name",
    "email": "new email",
    "phone": "new phone number",
    "state": "new state(open or closed)",
    "password": "new password",
    "counties": [{
      "name": "new county name"
    }]
  }
```





## public api:
### register for admin:
		http://localhost:8080/auth/manager/register
   ```json
   {
    "username": "username",
    "password": "password"
   }
   ```
### register for store:
		http://localhost:8080/auth/register
  ```json
  {
    "storeName": "store name",
    "userName": "username",
    "email": "email",
    "phone": "phone number",
    "password": "password",
    "counties": [{
      "name": "county name"
    }]
  }
```
	
### County:
		http://localhost:8080/county/allCounties display all counties
		http://localhost:8080/county/{name} find county by name

### type:
		http://localhost:8080/type/allTypes  display all type
		http://localhost:8080/type /{name} find type by name
### unit:
		http://localhost:8080/unit/allUnits 	 display all units
		http://localhost:8080/unit/{name} 	find c unit by name
	
### order:
		http://localhost:8080/order/add
  ```json
  {
    "email": "email",
    "totalPrice": "price",
    "customer": "customer name",
    "address1": "address1",
    "city": "city",
    "zip": "zip",
    "products": "list of products",
    "store" : "store_id"
    
  }
```
		http://localhost:8080/order/delete/{id}
    http://localhost:8080/order/all		displays all orders,
    http://localhost:8080/order/findByStore/{store}	{store} is store id
### Product:
    http://localhost:8080/product/list/{store}	find all products in a store {store} is store id
	  http://localhost:8080/product/search/{id}		search product by product id

### Store:

	http://localhost:8080/store/allStores				find all stores
  http://localhost:8080/store/searchByCounty/{county}	find stores by county name, 	
	http://localhost:8080/store/searchByUsr/{username}
	http://localhost:8080/store/searchByName/{storeName}

  http://localhost:8080/secure/store/validateByUsrEmail return a store if validated else null
   ```json
   {
    "username": "username",
    "email": "email"
   }
   ```

  http://localhost:8080/secure/store/validatePassword		return a store if validated else null
  ```json
     {
      "username": "username",
      "password": "password"
     }
  ```
	
  http://localhost:8080/secure/store/changePassword	change password
  ```json
   {
    "id": "store_id",
    "password": "new password"
   }
  ```




