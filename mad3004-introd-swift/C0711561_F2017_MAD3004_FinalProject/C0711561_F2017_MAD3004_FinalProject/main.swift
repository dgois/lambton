
var aEmp = [Employee]()


// create fulltime
var ft1 = FullTime() // default constructor
ft1.name = "Janet"
ft1.age = 20
ft1.salary = 50000
ft1.bonus = 2000

var vRef: Vehicle = Car(pMake: "Ferrari", pModel: "458", pPlate: "GGJD234", pTrunkSize: 180.32, pGear: "Automatic", pDoorsNumber: 4)
ft1.v = vRef

aEmp.append(ft1)

// create parttime
var pt1 : PartTime

vRef = Car(pMake: "Porsche", pModel: "Carrera", pPlate: "GGRT123", pTrunkSize: 150, pGear: "Automatic", pDoorsNumber: 4)

pt1 = PartTime(ppName: "Matthew", ppAge: 10, pHourlyRate: 100, pNumberHoursWorked: 2, ppV: vRef)

aEmp.append(pt1)

var pt2 : PartTime

pt2 = CommissionBasedPartTime(ppName: "Mark", ppAge: 22, pHourlyRate: 20, pNumberHoursWorked: 80, cbCommision: 1000)

aEmp.append(pt2)
//
//var pt3 : PartTime
//
//pt3 = FixedBasedPartTime(ppName: "Tom", ppAge: 27, pHourlyRate: 25, pNumberHoursWorked: 85, fbFixedBasedPartTime: 800)
//
//aEmp.append(pt3)
//// create intern
//
//vRef = Car(pMake: "BMW", pModel: "X6", pPlate: "AART143", pTrunkSize: 180.32, pGear: "Automatic", pDoorsNumber: 4)
//var it1 = Intern(pName: "Loonie", pAge: 15, pSchool: "WoofCenter", ppV: vRef)
//
//aEmp.append(it1)
//
////create intern
//
//var it2 = Intern(pName: "Toonie", pAge: 15, pSchool: "JKWoofCenter")
//aEmp.append(it2)
//


// calculate payroll

var totalPR : Double = 0.0
var e : Employee

for i in 0..<aEmp.count {

    e = aEmp[i]
    print(e.printMyData())
    let earn = e.calcEarnings()

    totalPR = totalPR + earn
}

print("\n-------------------------------------------")
print("\n")
print ("TOTAL PAYROLL: \(totalPR)")

























