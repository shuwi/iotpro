package cn.iot.ipro.dao;

import cn.iot.ipro.entity.InviteCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteCodeRepository extends JpaRepository<InviteCode,Long> {
    InviteCode getByCodeVal(String codeValue);
}
