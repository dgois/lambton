//: Playground - noun: a place where people can play

import UIKit

class Manufacturer {
    
    static var autoincrement = 0
    static var manufacturers = Set<String>()
    
    let manufacturerId: Int
    var manufacturerName: String
    
    // Constructor to create a new Manufacturer, use the autoincrement to set id
    init(name: String) {
        Manufacturer.autoincrement += 1
        self.manufacturerId = Manufacturer.autoincrement
        self.manufacturerName = name
        Manufacturer.manufacturers.insert(name)
    }
    
    // Get the manufacturer Id
    func getManufacturerId() -> Int {
        return self.manufacturerId
    }
    
    // Change the manufacturer name
    func setManufacturerName(name: String) {
        self.manufacturerName = name
    }
    
    // Get the manufacturer name
    func getManufacturerName() -> String {
        return self.manufacturerName
    }
    
    // Print all details of Manufacturer
    func print() {
        Swift.print("Manufacturer Id: \(manufacturerId) | Manufacturer Name: \(manufacturerName)")
    }
    
    static func printAllManufacturers() {
        for manufacturer in Manufacturer.manufacturers {
            Swift.print(manufacturer)
        }
    }
}

class Product : Manufacturer {
    static var autoincremet = 0
    
    let productId: Int
    var productName: String
    var productPrice: Double
    var productQuantity: Int
    
    // Constructor to initialize Product, use the autoincrement to set id
    init(name: String, price: Double, quantity: Int, manufacturer: String) {
        Product.autoincremet += 1;
        self.productId = Product.autoincremet
        self.productName = name
        self.productPrice = price
        self.productQuantity = quantity
        
        super.init(name: manufacturer)
    }
    
    // Allow to change the product name
    func setProductName(name: String) {
        self.productName = name
    }
    
    func getProductName() -> String {
        return self.productName
    }
    
    // Allow to change the product price
    func setProductPrice(price: Double) {
        self.productPrice = price
    }
    
    func getProductPrice() -> Double {
        return self.productPrice
    }
    
    // Override Manufacturer print method to show Product details
    override func print() {
        Swift.print("Product Id: \(productId) | Product Name: \(productName) | Product Price: $ \(productPrice) | Product Quantity \(productQuantity)")
    }
}

class Order {
    static var autoincremet = 0
    
    let orderId: Int
    var orderDate = Date()
    
    // After add a new product, calculate the total amount of order
    var orderProducts = [Product]() {
        didSet {
            if let product = oldValue.last {
                 orderTotal += (product.productPrice * Double(product.productQuantity))
            }
        }
    }
    var orderTotal = Double()
    
    // Constructor for Order object, use the autoincrement to set id
    init() {
        Order.autoincremet += 1
        orderId = Order.autoincremet
    }
    
    func getOrderId() -> Int {
        return self.orderId
    }
    
    func getOrderDate() -> Date {
        return self.orderDate
    }
    
    func getOrderProducts() -> [Product] {
        return self.orderProducts
    }
    
    // Add a new product
    func addProduct(product: Product) {
        orderProducts.append(product)
    }
    
    // Show all informations from a product
    func print() {
        if orderProducts.count > 0 {
            Swift.print("Order Id: \(orderId) | Order Date: \(orderDate)")
            Swift.print("\n")
            
            Swift.print("Products:")
            for product in orderProducts {
                product.print()
            }
            
            Swift.print("\n")
            Swift.print("Order Total: $ \(orderTotal)")
            Swift.print("\n")
        } else {
            Swift.print("There is no products!")
        }
    }
    
    // Static function to print all information from a set of orders
    static func printOrders(orders: [Int: Order]) {
        Swift.print("ORDERS ****************************************************************")
        for order in orders.values {
            order.print()
            Swift.print("***********************************************************************")
            Swift.print("\n")
        }
    }
}

// Creating products
var p1 = Product(name: "Hard drive", price: 120.00, quantity: 10, manufacturer: "Best Buy")
var p2 = Product(name: "ZIP drive", price: 90.00, quantity: 10, manufacturer: "Informatics Supply")
var p3 = Product(name: "Floppy disk", price: 50.00, quantity: 10, manufacturer: "Best Buy")
var p4 = Product(name: "Monitor", price: 300.00, quantity: 10, manufacturer: "Informatics Supply")
var p5 = Product(name: "iPhone 7 Plus", price: 1200.00, quantity: 10, manufacturer:"Informatics Supply")

// Creating orders
var o1 = Order()

o1.addProduct(product: p1)
o1.addProduct(product: p2)
o1.addProduct(product: p3)

var o2 = Order()

o2.addProduct(product: p2)
o2.addProduct(product: p5)
o2.addProduct(product: p4)
o2.addProduct(product: p1)

var o3 = Order()

o3.addProduct(product: p1)
o3.addProduct(product: p2)
o3.addProduct(product: p3)
o3.addProduct(product: p4)
o3.addProduct(product: p5)

var o4 = Order()

o4.addProduct(product: p1)
o4.addProduct(product: p3)
o4.addProduct(product: p4)
o4.addProduct(product: p5)

// Creating the order dictionary
var orders = [Int: Order]()
orders[o1.getOrderId()] = o1
orders[o2.getOrderId()] = o2
orders[o3.getOrderId()] = o3

// Searching for a order in the dictionary
var foundOrder = orders[o2.getOrderId()]
//foundOrder?.print()

// Print all orders
Order.printOrders(orders: orders)

Manufacturer.printAllManufacturers()
