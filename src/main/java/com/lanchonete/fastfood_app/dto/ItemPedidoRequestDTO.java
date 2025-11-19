    package com.lanchonete.fastfood_app.dto;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.UUID;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ItemPedidoRequestDTO {
        private UUID produtoId;
        private Integer quantidade;
    }