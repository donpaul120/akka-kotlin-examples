package com.vasconsolutions.customers

import javax.persistence.*

@NamedQueries(NamedQuery(name = "countCustomers", query = "SELECT COUNT(*) AS count FROM Customer"))
@Entity
@Table(name = "C_CONS")
data class Customer(
        @Id
        @Column(name = "CONS_ID")
        val id: Int,

        @Column(name = "DT_ID")
        val dTransformerId: Int? = null,

        @Column(name = "CUSTOMER_NO")
        val accountNo: String,

        @Column(name = "OLD_ACCOUNT_NO")
        val oldAccountNo: String?,

        @Column(name = "FIRST_NAME")
        val firstName: String,

        @Column(name = "LAST_NAME")
        val lastName: String?,

        @Column(name = "EMAIL")
        val email: String? = null,

        @Column(name = "CONS_NAME")
        val customerName: String,

        @Column(name = "MOBILE_NO")
        val mobileNo: String,

        @Column(name = "ELEC_ADDR")
        val plainAddress: String,

        @Column(name = "TARIFF")
        val tariff: String,

        @Column(name = "CUSTOMER_STATUS")
        val status: String,

        @Column(name = "ACCOUNT_TYPE")
        val accountType: String,

        @Column(name = "CUSTOMER_CATEGORY")
        val customerCategory: String,

        @Column(name = "PAYMENT_TYPE")
        val paymentType: String? = null,

        @Column(name = "ORG_NO")
        val orgNo: String
)