package com.mama.doc.dao;

import org.apache.ibatis.session.SqlSession;

import com.mama.doc.dto.DoctorDTO;
import com.mybatis.config.MyBatisConfig;

public class DoctorDAO {

	public SqlSession sqlSession;
	
	public DoctorDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
//	이메일 중복검사
	public int checkEmail(String doctorEmail) {
		int countDoctor = (Integer)sqlSession.selectOne("doctor.checkDoctorEmail", doctorEmail);
		int countMember = (Integer)sqlSession.selectOne("doctor.checkMemberEmail", doctorEmail);
		return countDoctor + countMember;
	}
	
//	닉네임 중복검사
	public int checkNickname(String doctorNickname) {
		int countDoctor = (Integer)sqlSession.selectOne("doctor.checkDoctorNickname", doctorNickname);
		int countMember = (Integer)sqlSession.selectOne("doctor.checkMemberNickname", doctorNickname);
		return countDoctor + countMember;
	}
	
//	회원가입
	public void join(DoctorDTO doctorDTO) {
		sqlSession.insert("doctor.join", doctorDTO);
	}
	
//	로그인
	public DoctorDTO login(DoctorDTO doctorDTO) {
		DoctorDTO doctor = sqlSession.selectOne("doctor.login", doctorDTO);
		return doctor;
	}
	
//	마이페이지
	public DoctorDTO inform(int doctorNumber) {
		DoctorDTO doctor = sqlSession.selectOne("doctor.inform", doctorNumber);
		return doctor;
	}
	
//	정보수정
	public void modifyInform(DoctorDTO doctorDTO) {
		sqlSession.update("doctor.modifyInform", doctorDTO);
	}
	
//	회원탈퇴
	public void quit(int doctorNumber) {
		sqlSession.delete("doctor.quit", doctorNumber);
	}
}
