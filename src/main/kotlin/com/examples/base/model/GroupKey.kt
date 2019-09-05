package com.examples.base.model

/**
 * @param crmId - The Group Id from CRM
 * @param iForceId - The Group Id on Iforce
 * @param type - Specify the group type e.g BU, UT etc
 */
data class GroupKey(val crmId: String, val iForceId: String, val type: String)