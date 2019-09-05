import com.vasconsolutions.customers.Customer

class Seeders {

    companion object {

        fun getCustomers(): List<Customer> {
            return listOf(
                    Customer(
                            id = 266464,
                            accountNo = "0100002121",
                            oldAccountNo = null,
                            customerName = "IKEJA ELECTRIC CHQ2",
                            firstName = "IKEJA ELECTRIC",
                            lastName = "CHQ2",
                            orgNo = "01030401",
                            email = "oogungbemi@ikejaelectric.com",
                            mobileNo = "09087406300",
                            plainAddress = "199,OBAFEMI-AWOLOWO WAY, , Ikeja , Lagos",
                            accountType = "NMD",
                            customerCategory = "residential",
                            status = "Active",
                            tariff = "R2TP",
                            dTransformerId = null
                    ),
                    Customer(
                            id = 266949,
                            dTransformerId = 266944,
                            accountNo = "0100002122",
                            oldAccountNo = "701229064601",
                            customerName = "YUSUF  ADIO MOHAMMED",
                            firstName = "YUSUF",
                            lastName = "MOHAMMED",
                            orgNo = "01030701",
                            email = null,
                            mobileNo = "08098363315",
                            plainAddress = "18, ABIODUN SOBAJO STREET AGIDINGBI   , Ikeja , Lagos",
                            accountType = "NMD",
                            customerCategory = "residential",
                            status = "Active",
                            tariff = "R2TP"
                    )
            )
        }

    }
}