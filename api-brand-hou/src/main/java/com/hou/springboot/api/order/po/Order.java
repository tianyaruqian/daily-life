package com.hou.springboot.api.order.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    @TableId(type=IdType.INPUT)

    private  String id;  //雪花算法，唯一id,java生成不自动
    //会员id；
   private Long memberId;
    //总价
    private BigDecimal totalPrice;
     //数量
    private Long totalCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date creatDate;
    //支付方式
    private Integer payType;
    //支付时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  payDate;
    //发货时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendDate;

    //交易完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDoneDate;

    //失败时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date  orderErrorDate;
     //评论时间
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date evaluateDate;
    //收货人
    private String   consignee;
    //订单状态
    private Integer orderStatus;
    //失败状态
    private Integer errorStatus;
      //收获地址
    private String addressInfo;
    //邮寄方式
     private   String   postMethod;
    //邮费
    private BigDecimal postage;
    //发票
     private  Integer  invoice;
    //收件人电话
    private String ConsigneePhone;

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public Long getMemberId() {
  return memberId;
 }

 public void setMemberId(Long memberId) {
  this.memberId = memberId;
 }

 public BigDecimal getTotalPrice() {
  return totalPrice;
 }

 public void setTotalPrice(BigDecimal totalPrice) {
  this.totalPrice = totalPrice;
 }

 public Long getTotalCount() {
  return totalCount;
 }

 public void setTotalCount(Long totalCount) {
  this.totalCount = totalCount;
 }

 public Date getCreatDate() {
  return creatDate;
 }

 public void setCreatDate(Date creatDate) {
  this.creatDate = creatDate;
 }

 public Integer getPayType() {
  return payType;
 }

 public void setPayType(Integer payType) {
  this.payType = payType;
 }

 public Date getPayDate() {
  return payDate;
 }

 public void setPayDate(Date payDate) {
  this.payDate = payDate;
 }

 public Date getSendDate() {
  return sendDate;
 }

 public void setSendDate(Date sendDate) {
  this.sendDate = sendDate;
 }



 public Date getOrderDoneDate() {
  return orderDoneDate;
 }

 public void setOrderDoneDate(Date orderDoneDate) {
  this.orderDoneDate = orderDoneDate;
 }

 public Date getOrderErrorDate() {
  return orderErrorDate;
 }

 public void setOrderErrorDate(Date orderErrorDate) {
  this.orderErrorDate = orderErrorDate;
 }

 public Date getEvaluateDate() {
  return evaluateDate;
 }

 public void setEvaluateDate(Date evaluateDate) {
  this.evaluateDate = evaluateDate;
 }

 public String getConsignee() {
  return consignee;
 }

 public void setConsignee(String consignee) {
  this.consignee = consignee;
 }

 public Integer getOrderStatus() {
  return orderStatus;
 }

 public void setOrderStatus(Integer orderStatus) {
  this.orderStatus = orderStatus;
 }

 public Integer getErrorStatus() {
  return errorStatus;
 }

 public void setErrorStatus(Integer errorStatus) {
  this.errorStatus = errorStatus;
 }

 public String getAddressInfo() {
  return addressInfo;
 }

 public void setAddressInfo(String addressInfo) {
  this.addressInfo = addressInfo;
 }

 public String getPostMethod() {
  return postMethod;
 }

 public void setPostMethod(String postMethod) {
  this.postMethod = postMethod;
 }

 public BigDecimal getPostage() {
  return postage;
 }

 public void setPostage(BigDecimal postage) {
  this.postage = postage;
 }

 public Integer getInvoice() {
  return invoice;
 }

 public void setInvoice(Integer invoice) {
  this.invoice = invoice;
 }

 public String getConsigneePhone() {
  return ConsigneePhone;
 }

 public void setConsigneePhone(String consigneePhone) {
  ConsigneePhone = consigneePhone;
 }
}
