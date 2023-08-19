package br.com.pizzaria.repository;

import br.com.pizzaria.entity.Pedido;
import br.com.pizzaria.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    List<Pedido> findByDelivery(boolean delivery);

    List<Pedido> findByStatus(Status status);

    List<Pedido> findByCanceladoAndCadastroBetween(boolean cancelado, LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<Pedido> findByStatusAndCadastroBetween(Status status, LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<Pedido> findByCadastroBetween(LocalDateTime inicioDoDia, LocalDateTime fimDoDia);
}
