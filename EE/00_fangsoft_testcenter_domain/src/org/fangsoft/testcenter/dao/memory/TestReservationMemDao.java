package org.fangsoft.testcenter.dao.memory;

import org.fangsoft.testcenter.dao.TestReservationDao;
import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.testcenter.model.TestResult;

import java.util.ArrayList;
import java.util.List;

public class TestReservationMemDao implements TestReservationDao {
    public TestReservation findTestReservationByPK(int testResId) {
        return DataRepository.testReservationMapForPK.get(testResId);
    }
    public List<TestReservation> getTestReservation(String userId) {
        List<TestReservation> testResList=
                DataRepository.testReservationMapForUserId.get(userId);
        return testResList==null?new ArrayList<TestReservation>(0):testResList;
    }
    public List<TestReservation> getTestReservationNotInStatus(String userId, TestReservation.Status status) {
        ArrayList<TestReservation> testResList=new ArrayList<TestReservation>();
        List<TestReservation> userIdTestResList=
                DataRepository.testReservationMapForUserId.get(userId);
        if(userIdTestResList!=null){
            for(TestReservation testRes:userIdTestResList){
                if(testRes.getStatus()!=status){
                    testResList.add(testRes);
                }
            }
        }
        testResList.trimToSize();
        return testResList;
    }
    public void save(TestReservation testRes) {
        int testResId=testRes.getId()==0?1:testRes.getId();
        if(DataRepository.testReservationMapForPK.get(testResId)!=null){
            for(int id:DataRepository.testReservationMapForPK.keySet()){
                testResId+=id;
            }
        }
        testRes.setId(testResId);
        DataRepository.testReservationMapForPK.put(testRes.getId(), testRes);
        String userId=testRes.getCustomer().getUserId();
        if(DataRepository.testReservationMapForUserId.get(userId)==null){
            DataRepository.testReservationMapForUserId.put
                    (userId, new ArrayList<TestReservation>());
        }
        DataRepository.testReservationMapForUserId.get(userId).add(testRes);
    }

    @Override
    public boolean updateStatus(int trId, TestResult.Status status) {
        return false;
    }

    public boolean updateStatus(int testResId, TestReservation.Status status) {
        TestReservation testRes=
                DataRepository.testReservationMapForPK.get(testResId);
        if(testRes!=null){
            testRes.setStatus(status);
            return true;
        }
        return false;
    }


}
