//package com.zzx.tools.example;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class TestExample implements Serializable{
//
//    private static final long serialVersionUID = -7749284288661993144L;
//
//    /** 查询参数 */
//
//    private Long id;
//    private String gbName=null;
//    private String gbNo=null;
//    private Long status=0L;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getGbName() {
//        return gbName;
//    }
//
//    public void setGbName(String gbName) {
//        this.gbName = gbName;
//    }
//
//    public String getGbNo() {
//        return gbNo;
//    }
//
//    public void setGbNo(String gbNo) {
//        this.gbNo = gbNo;
//    }
//
//    public Long getStatus() {
//        return status;
//    }
//
//    public void setStatus(Long status) {
//        this.status = status;
//    }
//
//    public void setOredCriteria(List<TestExample.Criteria> oredCriteria) {
//        this.oredCriteria = oredCriteria;
//    }
//    /** end */
//
//
//    /*****初始分页信息****/
//    /***** 分页信息 ******/
//    private int page;
//    private int rows;
//    private int start;
//    private int end;
//    public int getEnd() {
//        end = page * rows;
//        return end;
//    }
//
//    public int getStart() {
//        start = (page - 1) * rows;
//        if (start < 0) {
//            start = 0;
//        }
//        return start;
//    }
//
//    public int getPage() {
//        return page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public int getRows() {
//        return rows;
//    }
//
//    public void setRows(int rows) {
//        this.rows = rows;
//    }
//
//    public void setStart(int start) {
//        this.start = start;
//    }
//
//    public void setEnd(int end) {
//        this.end = end;
//    }
//
//    /****** 分页信息 ******/
//
//    private String orderByClause;
//
//    private boolean distinct;
//
//    private List<Criteria> oredCriteria;
//
//    public TestExample() {
//        oredCriteria = new ArrayList<Criteria>();
//    }
//
//    public void setOrderByClause(String orderByClause) {
//        this.orderByClause = orderByClause;
//    }
//
//    public String getOrderByClause() {
//        return orderByClause;
//    }
//
//    public void setDistinct(boolean distinct) {
//        this.distinct = distinct;
//    }
//
//    public boolean isDistinct() {
//        return distinct;
//    }
//
//    public List<Criteria> getOredCriteria() {
//        return oredCriteria;
//    }
//
//    public void or(Criteria criteria) {
//        oredCriteria.add(criteria);
//    }
//
//    public Criteria or() {
//        Criteria criteria = createCriteriaInternal();
//        oredCriteria.add(criteria);
//        return criteria;
//    }
//
//    public Criteria createCriteria() {
//        Criteria criteria = createCriteriaInternal();
//        if (oredCriteria.size() == 0) {
//            oredCriteria.add(criteria);
//        }
//        return criteria;
//    }
//
//    private Criteria createCriteriaInternal() {
//        Criteria criteria = new Criteria();
//        return criteria;
//    }
//
//    public void clear() {
//        oredCriteria.clear();
//        orderByClause = null;
//        distinct = false;
//    }
//
//    private abstract static class GeneratedCriteria implements Serializable{
//        /**
//		 * @dell
//		 */
//		private static final long serialVersionUID = -4938618067001850237L;
//		private List<Criterion> criteria;
//
//        private GeneratedCriteria() {
//            super();
//            criteria = new ArrayList<Criterion>();
//        }
//
//        public boolean isValid() {
//            return criteria.size() > 0;
//        }
//
//        public List<Criterion> getAllCriteria() {
//            return criteria;
//        }
//
//        public List<Criterion> getCriteria() {
//            return criteria;
//        }
//
//        private void addCriterion(String condition) {
//            if (condition == null) {
//                throw new RuntimeException("Value for condition cannot be null");
//            }
//            criteria.add(new Criterion(condition));
//        }
//
//        private void addCriterion(String condition, Object value, String property) {
//            if (value == null) {
//                throw new RuntimeException("Value for " + property + " cannot be null");
//            }
//            criteria.add(new Criterion(condition, value));
//        }
//
//        private void addCriterion(String condition, Object value1, Object value2, String property) {
//            if (value1 == null || value2 == null) {
//                throw new RuntimeException("Between values for " + property + " cannot be null");
//            }
//            criteria.add(new Criterion(condition, value1, value2));
//        }
//
//        public Criteria andIdIsNull() {
//            addCriterion("ID is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdIsNotNull() {
//            addCriterion("ID is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdEqualTo(Short value) {
//            addCriterion("ID =", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotEqualTo(Short value) {
//            addCriterion("ID <>", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdGreaterThan(Short value) {
//            addCriterion("ID >", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdGreaterThanOrEqualTo(Short value) {
//            addCriterion("ID >=", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdLessThan(Short value) {
//            addCriterion("ID <", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdLessThanOrEqualTo(Short value) {
//            addCriterion("ID <=", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdIn(List<Short> values) {
//            addCriterion("ID in", values, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotIn(List<Short> values) {
//            addCriterion("ID not in", values, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdBetween(Short value1, Short value2) {
//            addCriterion("ID between", value1, value2, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotBetween(Short value1, Short value2) {
//            addCriterion("ID not between", value1, value2, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameIsNull() {
//            addCriterion("GB_NAME is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameIsNotNull() {
//            addCriterion("GB_NAME is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameEqualTo(String value) {
//            addCriterion("GB_NAME =", value, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameNotEqualTo(String value) {
//            addCriterion("GB_NAME <>", value, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameGreaterThan(String value) {
//            addCriterion("GB_NAME >", value, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameGreaterThanOrEqualTo(String value) {
//            addCriterion("GB_NAME >=", value, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameLessThan(String value) {
//            addCriterion("GB_NAME <", value, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameLessThanOrEqualTo(String value) {
//            addCriterion("GB_NAME <=", value, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameLike(String value) {
//            addCriterion("GB_NAME like", value, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameNotLike(String value) {
//            addCriterion("GB_NAME not like", value, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameIn(List<String> values) {
//            addCriterion("GB_NAME in", values, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameNotIn(List<String> values) {
//            addCriterion("GB_NAME not in", values, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameBetween(String value1, String value2) {
//            addCriterion("GB_NAME between", value1, value2, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNameNotBetween(String value1, String value2) {
//            addCriterion("GB_NAME not between", value1, value2, "gbName");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoIsNull() {
//            addCriterion("GB_NO is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoIsNotNull() {
//            addCriterion("GB_NO is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoEqualTo(String value) {
//            addCriterion("GB_NO =", value, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoNotEqualTo(String value) {
//            addCriterion("GB_NO <>", value, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoGreaterThan(String value) {
//            addCriterion("GB_NO >", value, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoGreaterThanOrEqualTo(String value) {
//            addCriterion("GB_NO >=", value, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoLessThan(String value) {
//            addCriterion("GB_NO <", value, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoLessThanOrEqualTo(String value) {
//            addCriterion("GB_NO <=", value, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoLike(String value) {
//            addCriterion("GB_NO like", value, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoNotLike(String value) {
//            addCriterion("GB_NO not like", value, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoIn(List<String> values) {
//            addCriterion("GB_NO in", values, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoNotIn(List<String> values) {
//            addCriterion("GB_NO not in", values, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoBetween(String value1, String value2) {
//            addCriterion("GB_NO between", value1, value2, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andGbNoNotBetween(String value1, String value2) {
//            addCriterion("GB_NO not between", value1, value2, "gbNo");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusIsNull() {
//            addCriterion("STATUS is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusIsNotNull() {
//            addCriterion("STATUS is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusEqualTo(Long value) {
//            addCriterion("STATUS =", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusNotEqualTo(Long value) {
//            addCriterion("STATUS <>", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusGreaterThan(Long value) {
//            addCriterion("STATUS >", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusGreaterThanOrEqualTo(Long value) {
//            addCriterion("STATUS >=", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusLessThan(Long value) {
//            addCriterion("STATUS <", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusLessThanOrEqualTo(Long value) {
//            addCriterion("STATUS <=", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusLike(Long value) {
//            addCriterion("STATUS like", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusNotLike(Long value) {
//            addCriterion("STATUS not like", value, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusIn(List<Long> values) {
//            addCriterion("STATUS in", values, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusNotIn(List<Long> values) {
//            addCriterion("STATUS not in", values, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusBetween(Long value1, Long value2) {
//            addCriterion("STATUS between", value1, value2, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andStatusNotBetween(Long value1, Long value2) {
//            addCriterion("STATUS not between", value1, value2, "status");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtIsNull() {
//            addCriterion("CREATED_AT is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtIsNotNull() {
//            addCriterion("CREATED_AT is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtEqualTo(Date value) {
//            addCriterion("CREATED_AT =", value, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtNotEqualTo(Date value) {
//            addCriterion("CREATED_AT <>", value, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtGreaterThan(Date value) {
//            addCriterion("CREATED_AT >", value, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
//            addCriterion("CREATED_AT >=", value, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtLessThan(Date value) {
//            addCriterion("CREATED_AT <", value, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
//            addCriterion("CREATED_AT <=", value, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtIn(List<Date> values) {
//            addCriterion("CREATED_AT in", values, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtNotIn(List<Date> values) {
//            addCriterion("CREATED_AT not in", values, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtBetween(Date value1, Date value2) {
//            addCriterion("CREATED_AT between", value1, value2, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
//            addCriterion("CREATED_AT not between", value1, value2, "createdAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByIsNull() {
//            addCriterion("CREATED_BY is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByIsNotNull() {
//            addCriterion("CREATED_BY is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByEqualTo(String value) {
//            addCriterion("CREATED_BY =", value, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByNotEqualTo(String value) {
//            addCriterion("CREATED_BY <>", value, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByGreaterThan(String value) {
//            addCriterion("CREATED_BY >", value, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
//            addCriterion("CREATED_BY >=", value, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByLessThan(String value) {
//            addCriterion("CREATED_BY <", value, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByLessThanOrEqualTo(String value) {
//            addCriterion("CREATED_BY <=", value, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByLike(String value) {
//            addCriterion("CREATED_BY like", value, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByNotLike(String value) {
//            addCriterion("CREATED_BY not like", value, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByIn(List<String> values) {
//            addCriterion("CREATED_BY in", values, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByNotIn(List<String> values) {
//            addCriterion("CREATED_BY not in", values, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByBetween(String value1, String value2) {
//            addCriterion("CREATED_BY between", value1, value2, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreatedByNotBetween(String value1, String value2) {
//            addCriterion("CREATED_BY not between", value1, value2, "createdBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtIsNull() {
//            addCriterion("CHANGED_AT is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtIsNotNull() {
//            addCriterion("CHANGED_AT is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtEqualTo(Date value) {
//            addCriterion("CHANGED_AT =", value, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtNotEqualTo(Date value) {
//            addCriterion("CHANGED_AT <>", value, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtGreaterThan(Date value) {
//            addCriterion("CHANGED_AT >", value, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtGreaterThanOrEqualTo(Date value) {
//            addCriterion("CHANGED_AT >=", value, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtLessThan(Date value) {
//            addCriterion("CHANGED_AT <", value, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtLessThanOrEqualTo(Date value) {
//            addCriterion("CHANGED_AT <=", value, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtIn(List<Date> values) {
//            addCriterion("CHANGED_AT in", values, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtNotIn(List<Date> values) {
//            addCriterion("CHANGED_AT not in", values, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtBetween(Date value1, Date value2) {
//            addCriterion("CHANGED_AT between", value1, value2, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedAtNotBetween(Date value1, Date value2) {
//            addCriterion("CHANGED_AT not between", value1, value2, "changedAt");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByIsNull() {
//            addCriterion("CHANGED_BY is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByIsNotNull() {
//            addCriterion("CHANGED_BY is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByEqualTo(String value) {
//            addCriterion("CHANGED_BY =", value, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByNotEqualTo(String value) {
//            addCriterion("CHANGED_BY <>", value, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByGreaterThan(String value) {
//            addCriterion("CHANGED_BY >", value, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByGreaterThanOrEqualTo(String value) {
//            addCriterion("CHANGED_BY >=", value, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByLessThan(String value) {
//            addCriterion("CHANGED_BY <", value, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByLessThanOrEqualTo(String value) {
//            addCriterion("CHANGED_BY <=", value, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByLike(String value) {
//            addCriterion("CHANGED_BY like", value, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByNotLike(String value) {
//            addCriterion("CHANGED_BY not like", value, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByIn(List<String> values) {
//            addCriterion("CHANGED_BY in", values, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByNotIn(List<String> values) {
//            addCriterion("CHANGED_BY not in", values, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByBetween(String value1, String value2) {
//            addCriterion("CHANGED_BY between", value1, value2, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andChangedByNotBetween(String value1, String value2) {
//            addCriterion("CHANGED_BY not between", value1, value2, "changedBy");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionIsNull() {
//            addCriterion("VERSION is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionIsNotNull() {
//            addCriterion("VERSION is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionEqualTo(Short value) {
//            addCriterion("VERSION =", value, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionNotEqualTo(Short value) {
//            addCriterion("VERSION <>", value, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionGreaterThan(Short value) {
//            addCriterion("VERSION >", value, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionGreaterThanOrEqualTo(Short value) {
//            addCriterion("VERSION >=", value, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionLessThan(Short value) {
//            addCriterion("VERSION <", value, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionLessThanOrEqualTo(Short value) {
//            addCriterion("VERSION <=", value, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionIn(List<Short> values) {
//            addCriterion("VERSION in", values, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionNotIn(List<Short> values) {
//            addCriterion("VERSION not in", values, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionBetween(Short value1, Short value2) {
//            addCriterion("VERSION between", value1, value2, "version");
//            return (Criteria) this;
//        }
//
//        public Criteria andVersionNotBetween(Short value1, Short value2) {
//            addCriterion("VERSION not between", value1, value2, "version");
//            return (Criteria) this;
//        }
//    }
//
//    public static class Criteria extends GeneratedCriteria implements Serializable{
//
//        private static final long serialVersionUID = 188327725199798196L;
//
//        private Criteria() {
//            super();
//        }
//    }
//
//    public static class Criterion  implements Serializable{
//
//        private static final long serialVersionUID = 2166740836542466677L;
//        private String condition;
//
//        private Object value;
//
//        private Object secondValue;
//
//        private boolean noValue;
//
//        private boolean singleValue;
//
//        private boolean betweenValue;
//
//        private boolean listValue;
//
//        private String typeHandler;
//
//        public String getCondition() {
//            return condition;
//        }
//
//        public Object getValue() {
//            return value;
//        }
//
//        public Object getSecondValue() {
//            return secondValue;
//        }
//
//        public boolean isNoValue() {
//            return noValue;
//        }
//
//        public boolean isSingleValue() {
//            return singleValue;
//        }
//
//        public boolean isBetweenValue() {
//            return betweenValue;
//        }
//
//        public boolean isListValue() {
//            return listValue;
//        }
//
//        public String getTypeHandler() {
//            return typeHandler;
//        }
//
//        private Criterion(String condition) {
//            super();
//            this.condition = condition;
//            this.typeHandler = null;
//            this.noValue = true;
//        }
//
//        private Criterion(String condition, Object value, String typeHandler) {
//            super();
//            this.condition = condition;
//            this.value = value;
//            this.typeHandler = typeHandler;
//            if (value instanceof List<?>) {
//                this.listValue = true;
//            } else {
//                this.singleValue = true;
//            }
//        }
//
//        private Criterion(String condition, Object value) {
//            this(condition, value, null);
//        }
//
//        private Criterion(String condition, Object value, Object secondValue, String typeHandler) {
//            super();
//            this.condition = condition;
//            this.value = value;
//            this.secondValue = secondValue;
//            this.typeHandler = typeHandler;
//            this.betweenValue = true;
//        }
//
//        private Criterion(String condition, Object value, Object secondValue) {
//            this(condition, value, secondValue, null);
//        }
//    }
//}