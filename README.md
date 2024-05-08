# Elektromart-ecom-website

## Description
Brief overview of the project, its purpose, and key features.

## Installation Instructions
Step-by-step guide on how to install and set up the project locally.

## Usage
HTTP requests for each entity's endpoint at http://localhost:8080:

### HTTP GET requests

- Fetch all registered configurations:
    * Path: `/<entity_name>/` or `/<entity_name>`
    * Example: `/brand/` or `/brand`

- Fetch one specific registered configuration:
    * Path: `/<entity_name>/<entity_name>_id={id_number}`
    * Example: `/brand/brand_id=2`

- Get the description of a specific brand or category name (Only implemented for the Brand and Category entities):
    * Path: `/<entity_name>/name=<category_or_brand_name>`
    * Example: `/brand/name=Apple`


### HTTP POST requests

Valid JSON structs:
- User:
```json
{
  "userID": 1,
  "password": "faee49b8b060d1d8cd263fcf9426fc7c4ad1ad98bfad7f7761c93cca09824043",
  "email": "dc@doublechamp.com",
  "firstName": "Daniel",
  "lastName": "Cormier"
}
```

- Brand:
```json
{
  "name": "Apple",
  "description": "Brand that makes smartphones",
  "brandId": 1
}
```

- Category:
```json
{
  "name": "Appliances",
  "description": "Ovens, refrigerators, dishwasher, washing machine, dryer, etc",
  "categoryId": 1
}
```

- Product:
```json
{
  
}
```

- Promotion
```json
{
  "promotionID": 1,
  "productID": 6,
  "description": "60 percent off through july 2024",
  "discountType": "percent",
  "discountValue": 10.0,
  "startDate": "2024-05-01",
  "endDate": "2024-07-31"
}
```

- Review
```json
{
  "reviewID": 2,
  "userID": 3,
  "productID": 2,
  "rating": 5,
  "reviewText": "Great product, good quality",
  "reviewDate": "2024-02-12"
}
```


- Shipping
```json
{
  "shippingID": 1,
  "orderID": 1,
  "shippingMethod": "Standard Shipping",
  "shippingCost": 10.0
}
```

- Order
```json
{
  "orderDate": "2024-05-07",
  "orderStatus": "Shipped",
  "orderId": 1,
  "userId": 1,
  "orderAmount": 100.0
}
```

- OrderItem
```json
{
  "orderID": 1,
  "productID": 2,
  "itemQuantity": 5,
  "itemSubtotal": 32500
}
```

- Inventory
```json
{
  
}
```

- Payment
```json

```

- Add a new configuration with a specific entity request body:
    * Path: `/<entity_name>/` or `/<entity_name>`
    * Example: `/brand/`
       * Request body: 
       ```
       {
       "name": "Microsoft",
            "description": "Brand that makes computer technology",
            "brandId": 13
       }
       ```


### HTTP DELETE requests

- Delete a registration of a specific entity with a given id_number
    * Path: `/<entity_name>/<entity_name>_id={id_number}`
    * Example: `/brand/brand_id=1`


## Technologies Used
List of technologies, frameworks, and libraries used in the project.

## Contact Information

If you have any questions or issues, please contact us at:

- Emil Klevgård-Slåttsveen: <emilkle@stud.ntnu.no>
