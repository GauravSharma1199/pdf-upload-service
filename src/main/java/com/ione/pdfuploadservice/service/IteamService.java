package com.ione.pdfuploadservice.service;

import com.ione.commondata.dto.LogDTO;
import com.ione.commondata.repo.ItemModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Clob;

@Service
public class IteamService {

	@Autowired
	private ItemModuleRepository itemmoduleRepo;

	public Clob getItemModuleData() {
		return itemmoduleRepo.fetchItemRecords();
	}

	public Clob updateItemModuleData() {
		return itemmoduleRepo.updateItemRecords();
	}

	public void updateCRMID(String p_item_id, String p_organization_id, String p_crm_id, String p_message) {

		itemmoduleRepo.updateCRMID(p_item_id, p_organization_id, p_crm_id, p_message);
	}

	public void updatedCRMIDRes(String p_crm_id, String p_message) {

		itemmoduleRepo.updatedCRMIDRes(p_crm_id, p_message);

	}

	public Clob salesItemCreationData() {
		return itemmoduleRepo.salesRecordCreation();
	}

	public Clob salesUpateCreationData() {

		return itemmoduleRepo.salesRecordUpdate();
	}

	public void updatedSalesRecords(String p_crm_id, String p_message, String p_sales_Rep_ID) {

		itemmoduleRepo.updatedSalesItemRecords(p_crm_id, p_message, p_sales_Rep_ID);

	}

	public Clob getDispatchModuleCreation() {

		return itemmoduleRepo.dispatchItemCreation();

	}

	public Clob invoiceItemCreationData() {
		return itemmoduleRepo.invoiceItemCreation();
	}

	public Clob getCreditDebitData() {
		return itemmoduleRepo.getCreditDebitData();
	}

	public void updatedInvoiceRecords(String p_order_number, String p_organization_id, String p_crm_id,
			String p_message) {

		itemmoduleRepo.updatedInvoiceItemRecords(p_order_number, p_organization_id, p_crm_id, p_message);

	}
	public void updateCRM(String p_receivable_id, String p_organization_id, String p_crm_id,
									  String p_message) {

		itemmoduleRepo.updateCRM(p_receivable_id, p_organization_id, p_crm_id, p_message);

	}

	public void updatePDF(String p_record_no, String p_status, String p_message) {

		itemmoduleRepo.updatePDF(p_record_no, p_status, p_message);

	}
	public void customerUpdateAck(String p_record_no, String p_status, String p_message) {

		itemmoduleRepo.customerUpdateAck(p_record_no, p_status, p_message);

	}
	public void orderUpdateAck(String p_record_no, String p_status, String p_message) {

		itemmoduleRepo.orderUpdateAck(p_record_no, p_status, p_message);

	}


	public Clob orderItemCreationData() {
		return itemmoduleRepo.orderCreationPayload();
	}

	// orderUpdatePayload

	public Clob orderItemUpdateData() {
		return itemmoduleRepo.orderUpdatePayload();
	}

	public Clob stockCreationPayload() {
		return itemmoduleRepo.stockCreationPayload();
	}
	
	public void updatedOrderItemsRecords(String p_order_number, String p_crm_id, String p_message,
			String p_organization_id) {

		itemmoduleRepo.updatedOrderCreationRecords(p_order_number, p_crm_id, p_message, p_organization_id);
		

	}

	public void updatedOrderLineRecords(String p_order_number, String p_line_num, String p_crm_id, String p_message,String p_organization_id) {

		itemmoduleRepo.updatedOrderLineRecords(p_order_number, p_line_num, p_crm_id, p_message,p_organization_id);

	}

	public Clob paymentCreationAPI() {

		return itemmoduleRepo.paymentPayload();
	}

	public Clob paymentUpdateAPI() {

		return itemmoduleRepo.paymentUpdatePayload();
	}

	public Clob pdfUploadAPI() {
		return itemmoduleRepo.pdfUploadPayload();
	}
	public void updatedPaymentRecords(String name, String p_organization_id, String invoiceno, String p_crm_id,
			String p_message) {

		itemmoduleRepo.updatePaymentRecords(name, p_organization_id, invoiceno, p_crm_id, p_message);

	}

	public void updatedCustomerRecords(String p_crm_id, String p_message, String p_location, String organization_id) {

		itemmoduleRepo.updatedCustomerRecords(p_crm_id, p_message, p_location, organization_id);

	}
	
	public void updateDispatchItem(String p_trx_line_id, String p_crm_id,String p_message) {

		itemmoduleRepo.updateDispatchItem(p_trx_line_id, p_crm_id, p_message);
	}

	public Clob customerCreationAPI() {

		return itemmoduleRepo.customerPayload();
	}

	public Clob customerUpdateAPI() {

		return itemmoduleRepo.customerUpdatePload();
	}

	public void updateItemCreationStart(String name) {

		itemmoduleRepo.updateItemCreationStart(name);
		
	}

	public void updateItemCreationEnd(String name) {

		itemmoduleRepo.updateItemCreationEnd(name);
	}

	public void start(String name) {

		itemmoduleRepo.updateItemCreationStart(name);

	}

	public void end(String name) {

		itemmoduleRepo.updateItemCreationEnd(name);
	}

	public void pushLog(LogDTO log) {

		itemmoduleRepo.pushLog(
				log.getId(),
				log.getParentId(),
				log.getApiName(),
				log.getProgramName(),
				log.getContent(),
				log.getDescription()
		);
	}
}
