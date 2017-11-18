import Foundation

var moto1: Vehicle = Motorcycle(pMake: "Yamaha", pModel: "2017 YZF-R1M", pPlate: "H5J4II1", pCylinderCapacity: 998, pType: "Racing", pRidingPosture: "Sport")
var moto2: Vehicle = Motorcycle(pMake: "Harley-Davidson", pModel: "750", pPlate: "DEG134H", pCylinderCapacity: 750, pType: "Street", pRidingPosture: "Standard")

var car1: Vehicle = Car(pMake: "Ferrari", pModel: "458", pPlate: "GGJD234", pTrunkSize: 180.32, pGear: "Automatic", pDoorsNumber: 4)
var car2: Vehicle = Car(pMake: "Porsche", pModel: "Carrera", pPlate: "GGRT123", pTrunkSize: 150, pGear: "Automatic", pDoorsNumber: 4)

var aEmp = [Employee]()

// create fulltime
var ft1 = FullTime() // default constructor
ft1.name = "Janet"
ft1.age = 20
ft1.salary = 50000
ft1.bonus = 2000
ft1.v = car1

aEmp.append(ft1)

var ft2: Employee = FullTime(ppName: "Maria", ppAge: 35, pSalary: 60000, pBonus: 6000)
aEmp.append(ft2)

// create parttime
var pt1: Employee = CommissionBasedPartTime(ppName: "Matthew", ppAge: 30, pHourlyRate: 100, pNumberHoursWorked: 20, ppV: car1, cbCommision: 20)
aEmp.append(pt1)

var pt2: Employee = CommissionBasedPartTime(ppName: "Mark", ppAge: 22, pHourlyRate: 20, pNumberHoursWorked: 80, ppV: moto1, cbCommision: 10)
aEmp.append(pt2)

var pt3: Employee = FixedBasedPartTime(ppName: "Tom", ppAge: 27, pHourlyRate: 25, pNumberHoursWorked: 85, ppV: moto2, fbFixedBasedPartTime: 800)
aEmp.append(pt3)

var pt4: Employee = FixedBasedPartTime(ppName: "Vivian", ppAge: 25, pHourlyRate: 70, pNumberHoursWorked: 164, fbFixedBasedPartTime: 1000)

// create intern

var it1: Employee = Intern(pName: "Loonie", pAge: 15, pSchool: "WoofCenter", ppV: car2, pFixedSalary: 1000.0)
aEmp.append(it1)

//create intern

var it2: Employee = Intern(pName: "Toonie", pAge: 15, pSchool: ", pFixedSalary: 1100.0)
aEmp.append(it2)

// calculate payroll

var totalPR : Double = 0.0
var e : Employee

for i in 0..<aEmp.count {

    e = aEmp[i]
    print(e.printMyData())
    let earn = e.calcEarnings()

    totalPR += earn
}

print("\n-------------------------------------------")
print("\n")
print ("TOTAL PAYROLL: \(Util.getInstant().toCurrencyFormatFrom(value: totalPR)) Canadian Dollars\n")

























