package kz.narxoz.kordim.repository;
import kz.narxoz.kordim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String username);
}



