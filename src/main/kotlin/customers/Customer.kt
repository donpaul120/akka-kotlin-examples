package customers

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customer")
data class Customer(
        @Id
        @Column(name = "CONS_ID")
        val id: String,

        @Column(name = "CUSTOMER_NO")
        val accountNo: String,

        @Column(name = "OLD_ACCOUNT_NO")
        val oldAccountNo: String,

        @Column(name = "FIRST_NAME")
        val firstName: String,

        @Column(name = "LAST_NAME")
        val lastName: String,

        @Column(name = "EMAIL")
        val email: String,

        @Column(name = "CONS_NAME")
        val customerName: String,

        @Column(name = "MOBILE_NO")
        val mobileNo: String,

        @Column(name = "ELEC_ADDR")
        val plainAddress: String,

        @Column(name = "TARIFF")
        val tariff: String,

        @Column(name="CUSTOMER_STATUS")
        val status: String,

        @Column(name="ORG_NO")
        val groupId: String
)