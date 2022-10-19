package ru.brinicle.myauthorizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brinicle.myauthorizationservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
