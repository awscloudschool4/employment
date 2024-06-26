package com.example.employment.service;

import com.example.employment.domain.apply.Apply;
import com.example.employment.domain.apply.dto.ApplyRequestDto;
import com.example.employment.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyRepository applyRepositoy;

    public List<Apply> findAll() {
        return applyRepositoy.findAll();
    }

    public String saveApply(ApplyRequestDto.ApplyReqInfo applyReqInfo) {
        int count= (int) applyRepositoy.count();
        Apply apply=new Apply(applyReqInfo,count);
        Apply save=applyRepositoy.save(apply);
        return save.getIndex()+" 번째 지원서 정보 저장 완료";
    }

    public String updatePass(ApplyRequestDto.ApplyPassInfo applyPassInfo) {
        Apply apply=applyRepositoy.findByIndex(applyPassInfo.getIndex());
        apply.setIsPass(applyPassInfo.getPass());
        applyRepositoy.save(apply);
        return apply.getName() + " 합격 여부 수정 완료";
    }

    public String deleteApply(int index) {
        Apply apply=applyRepositoy.findByIndex(index);
        applyRepositoy.delete(apply);
        return apply.getName() + " 지원서 삭제";
    }

    public Apply findByIndex(int index) {
        return applyRepositoy.findByIndex(index);
    }

    public List<Apply> findByJobName(ApplyRequestDto.ApplyNameInfo applyNameInfo) {
        String jobName=applyNameInfo.getName();
        List<Apply> applies=applyRepositoy.findAll();
        List<Apply> result=new ArrayList<>();
        for (int i = 0; i < applies.size(); i++) {
            if (Objects.equals(applies.get(i).getJobName(), jobName)) {
                result.add(applies.get(i));
            }
        }
        return result;
    }

    public List<Apply> findByName(ApplyRequestDto.ApplyNameInfo applyNameInfo) {
        String name=applyNameInfo.getName();
        List<Apply> applies=applyRepositoy.findAll();
        List<Apply> result=new ArrayList<>();
        for (int i = 0; i < applies.size(); i++) {
            if (Objects.equals(applies.get(i).getName(), name)) {
                result.add(applies.get(i));
            }
        }
        return result;
    }
}
