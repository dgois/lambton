

var aEmp = [Employee]()


// create fulltime
var ft1 = FullTime() // default constructor
ft1.name = "Janet"
ft1.age = 20
ft1.salary = 50000
ft1.bonus = 2000

var vRef = Vehicle(pMake: "Ferrari",pModel: "458")
ft1.v = vRef

aEmp.append(ft1)

// create parttime
var pt1 : PartTime

vRef = Vehicle(pMake: "Porsche", pModel: "Carrera")

pt1 = PartTime(ppName: "Matthew", ppAge: 10, pHourlyRate: 100, pNumberHoursWorked: 2, ppV: vRef)

aEmp.append(pt1)

//create intern

vRef = Vehicle(pMake: "BMW", pModel: "X6")
var it1 = Intern(pName: "Loonie", pAge: 15, pSchool: "WoofCenter", ppV: vRef)

aEmp.append(it1)

//create intern


var it2 = Intern(pName: "Toonie", pAge: 15, pSchool: "JKWoofCenter")
aEmp.append(it2)



// calculate payroll

var totalPR : Double = 0.0
var e : Employee

for i in 0..<aEmp.count {
    
    e = aEmp[i]
    var earn = e.calcEarnings()
    
//    print ("Name: \(e.name)")
//    print ("Age: \(e.age)")
    e.printMyData()
    // display vehicle
    if (e.v == nil) {
        print ("** Employee has not registered any vehicle ***")
    } else {
        print ("*** Employe has a Vehicle")
        print ("Make: \(e.v!.make)")
        print ("Model: \(e.v!.model)")
    }
    print ("Birth Year: " + String(e.calcBirthYear()))
    print ("Earnings: \(earn)")
    print ("**********************")
    
    totalPR = totalPR + earn
}

print ("TOTAL PAYROLL: \(totalPR)")

























