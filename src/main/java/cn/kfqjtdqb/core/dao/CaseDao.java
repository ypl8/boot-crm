package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.Case;

import java.util.List;

public interface CaseDao {

	List<Case> selectCaseList(Case caser);
	Integer selectCaseListCount(Case caser);
	Case getCaseById(Long id);
	void updateCase(Case caser);
	void deleteCase(Long id);
	int  addCase(Case caser);
}