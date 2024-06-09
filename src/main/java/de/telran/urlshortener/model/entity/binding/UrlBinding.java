package de.telran.urlshortener.model.entity.binding;

import de.telran.urlshortener.model.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data//это сокращенная аннотация, сочетающая возможности @ToString, @EqualsAndHashCode,
// @Getter @Setter и @RequiredArgsConstructor
@NoArgsConstructor//для создания конструктора без аргументов
@AllArgsConstructor(access = AccessLevel.PRIVATE)//генерирует конструктор с параметрами для всех полей класса
@Builder(toBuilder = true)//Флаг toBuilder = true добавляет функциональность, позволяющую
// модифицировать уже созданный объект с помощью нового builder-а пошагово
@Entity//определения класса, который должен быть отображен на таблицу в базе данных
// с помощью операций CRUD (Create, Read, Update, Delete).
@Table(name = "url_bindings")
@EqualsAndHashCode(exclude = "user")//указывает, что поле user не будет
// использоваться при сравнении и генерации хеш-кода
@ToString(exclude = "user")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "UrlBindings.withUser",
                attributeNodes = @NamedAttributeNode("user")
        )
})//Управляет тем, как JPA извлекает связанные сущности во время запроса к базе данных.
//Определяет, какие сущности и их атрибуты должны быть получены вместе
// с корневой сущностью в одном запросе.
public class UrlBinding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "original_url", nullable = false, length = 2048)
    private String originalUrl;
    @Column(name = "base_url", nullable = false)
     private String baseUrl;
    @Column(name = "path_prefix", nullable = false)
    private String pathPrefix;
    @Column(name = "uid", nullable = false, unique = true)
    private String uid;

    private Long count = 0L;

    @Column(name = "created_at")
    @Builder.Default
    @Getter @Setter
    private LocalDate creationDate = LocalDate.now();

    @Column(name = "expires_at")
    @Builder.Default
    private LocalDate expirationDate = LocalDate.now().plusDays(30);
    @Column(nullable = false)
    @Builder.Default
    private Boolean isClosed = false;

    @ManyToOne(fetch = FetchType.LAZY)
//    Ленивая загрузка: FetchType.LAZY указывает, что JPA не будет
//    автоматически загружать связанный объект из базы данных,
//    когда извлекается владеющий объект.
//    Загрузка по требованию: Когда происходит обращение к полю @ManyToOne
//    , JPA выполнит дополнительный запрос,
//    чтобы загрузить связанный объект из базы данных.
    @Getter @Setter
    private User user;

    public String getShort(){
        return this.getBaseUrl()+this.getPathPrefix()+this.getUid();
    }
}