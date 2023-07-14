package ru.Anastasia.telegrambot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Anastasia.telegrambot.entities.*;
import ru.Anastasia.telegrambot.repositories.*;

import java.util.Optional;

@SpringBootTest
 class FillingTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    /** Метод для создания объекта основной категории */
    private void createMainCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        
    }

    /** Метод для создания объекта подкатегории */
    private void createSubCategory(String name, Category parent) {
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        categoryRepository.save(category);
        
    }

    /** Метод для создания объекта товара */
    private void createProduct(Category category,String name, String desc,Double price){
        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(price);
        productRepository.save(product);

    }

    /** Метод для создания объекта клиент */
    private void createClient(Long extid,String fullName,String number,String address){
        Client client = new Client();
        client.setExternalId(extid);
        client.setFullName(fullName);
        client.setPhoneNumber(number);
        client.setAddress(address);
        clientRepository.save(client);
    }
    /** Метод для создания объекта заказ-товар */
    private void createOrderProduct(ClientOrder clientOrder,Product product,Long countProduct){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setClientOrder(clientOrder);
        orderProduct.setProduct(product);
        orderProduct.setCountProduct(countProduct);
        orderProductRepository.save(orderProduct);
    }

    /** Метод для создания объекта заказ-клиент */
    private void createClientOrder(Client client,Integer status, Double total){
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setClient(client);
        clientOrder.setStatus(status);
        clientOrder.setTotal(total);
        clientOrderRepository.save(clientOrder);
    }



    @Test
     void createCategoryObjects() {

        // Основные категории

        createMainCategory("Пицца");

        createMainCategory("Роллы");

        createMainCategory("Бургеры");

        createMainCategory("Напитки");

        // Подкатегории Роллы

        createSubCategory("Классические роллы", categoryRepository.findByName("Роллы"));

        createSubCategory("Запеченные роллы", categoryRepository.findByName("Роллы"));

        createSubCategory("Сладкие роллы", categoryRepository.findByName("Роллы"));

        createSubCategory("Наборы роллов", categoryRepository.findByName("Роллы"));

        //Подкатегории Бургеры

        createSubCategory("Классические бургеры", categoryRepository.findByName("Бургеры"));

        createSubCategory("Острые бургеры", categoryRepository.findByName("Бургеры"));

        // Подкатегории напитки
        
        createSubCategory("Газированные напитки", categoryRepository.findByName("Напитки"));

        createSubCategory("Энергетические напитки", categoryRepository.findByName("Напитки"));

        createSubCategory("Соки", categoryRepository.findByName("Напитки"));

        createSubCategory("Другие", categoryRepository.findByName("Напитки"));

    }

    @Test
    void createProductObjects(){

        /* Товары категории пицца */
        
        createProduct(categoryRepository.findByName("Пицца"), "Пепперони",
                "Сыр, копченые колбаски, томатный соус",350.00);

        createProduct(categoryRepository.findByName("Пицца"), "Авторская",
                "Грибы, помидоры, оливки, сыр, ветчина",500.00);

        createProduct(categoryRepository.findByName("Пицца"), "Вегетерианская",
                "Грибы, помидоры, болгарский перец, соус ореховый",350.00);


        /* Товары категории роллы */
        /* Товары подкатегории Классические роллы */
        
        createProduct(categoryRepository.findByName("Классические роллы"), "Филадельфия",
                "Лосось, крем чиз, рис, нори",450.99);

        createProduct(categoryRepository.findByName("Классические роллы"), "Калифорния",
                "Икра, угорь, крем чиз, рис, нори",399.99);

        createProduct(categoryRepository.findByName("Классические роллы"), "Филадельфия креветка",
                "Лосось,креветка, крем чиз, рис, нори",550.00);

        /* Товары подкатегории Запеченные роллы */

        createProduct(categoryRepository.findByName("Запеченные роллы"), "Ямми",
                "Угорь, сырная шапка, рис, нори",300.99);

        createProduct(categoryRepository.findByName("Запеченные роллы"), "Темпура",
                "Креветка, крем чиз, рис, нори",400.50);

        createProduct(categoryRepository.findByName("Запеченные роллы"), "Огонек",
                "Сырная шапка, мидия, крем чиз, рис, нори",350.50);

        /* Товары подкатегории Сладкие роллы */

        createProduct(categoryRepository.findByName("Сладкие роллы"), "Для нее",
                "Клубника, крем чиз, белый шоколад",250.99);

        createProduct(categoryRepository.findByName("Сладкие роллы"), "Для него",
                "Смородиновый джем, крем чиз, темный шоколад",249.99);

        createProduct(categoryRepository.findByName("Сладкие роллы"), "Пахлава",
                "Мед, тесто, грецкие орехи",300.00);

        /* Товары подкатегории Наборы роллов */

        createProduct(categoryRepository.findByName("Наборы роллов"), "Отпуск в Америке",
                "Ролл Филадельфия, ролл Калифорния",850.99);

        createProduct(categoryRepository.findByName("Наборы роллов"), "Романтический вечер",
                "Сладкие роллы для него и для нее",500.99);

        createProduct(categoryRepository.findByName("Наборы роллов"), "Горячий сет",
                "Ролл Темпура, ролл Огонек",700.99);

        /* Товары категории Бургеры */
        /* Товары подкатегории Классические бургеры */

        createProduct(categoryRepository.findByName("Классические бургеры"), "Чизбургер",
                "Говяжья котлета, сыр чеддер, салат, соус",300.00);

        createProduct(categoryRepository.findByName("Классические бургеры"), "ШефБургер",
                "Свиная котлета, сыр чеддер, помидор, огурец, салат, соус от шефа",400.00);

        createProduct(categoryRepository.findByName("Классические бургеры"), "Детский",
                "Куриная котлетка, помидорка, салатик, огурчик, соусик",250.15);

        /* Товары подкатегории Острые бургеры */

        createProduct(categoryRepository.findByName("Острые бургеры"), "Дьябло",
                "Говяжья котлета, сыр чеддер, салат, острый соус",470.99);

        createProduct(categoryRepository.findByName("Острые бургеры"), "Шеф с огнеметом",
                "Свиная котлета, сыр чеддер, помидор, огурец, салат, халапеньо, острый соус от шефа",459.99);

        createProduct(categoryRepository.findByName("Острые бургеры"), "Держись, брат",
                "Говяжья котлета, сыр чеддер, салат, халапеньо, острый соус, красный и черный перец\n" +
                        "p.s. Если съешь полностью - бургер за наш счет",599.99);

        /* Товары категории Напитки */
        /* Товары подкатегории Газированные напитки */

        createProduct(categoryRepository.findByName("Газированные напитки"), "Спрайт",
                "1 литр",150.00);

        createProduct(categoryRepository.findByName("Газированные напитки"), "Пепси-кола",
                "1 литр",170.00);

        createProduct(categoryRepository.findByName("Газированные напитки"), "Бонаква",
                "1 литр",115.00);

        /* Товары подкатегории Энергетические напитки */

        createProduct(categoryRepository.findByName("Энергетические напитки"), "Ред булл",
                "Черника/Кокос-ягоды 0.3 л.",170.00);

        createProduct(categoryRepository.findByName("Энергетические напитки"), "Берн",
                "Классический 0.45 л.",100.99);

        createProduct(categoryRepository.findByName("Энергетические напитки"), "Торнадо",
                "Кокос/Малина/Кофе 0.45 л.",90.99);

        /* Товары подкатегории Соки */

        createProduct(categoryRepository.findByName("Соки"), "Добрый",
                "Яблоко/Мультифрукт/Вишня/Апельсин 1 л.",150.99);

        createProduct(categoryRepository.findByName("Соки"), "Рич Бич",
                "Яблоко/Мультифрукт/Вишня/Апельсин 1 л.",250.99);

        createProduct(categoryRepository.findByName("Соки"), "ПочтиСок",
                "Яблоко/Мультифрукт/Вишня/Апельсин 1 л.",59.99);

        /* Товары подкатегории Другие */

        createProduct(categoryRepository.findByName("Другие"), "Пиво",
                "Хугарден, Эдельвейс, Кроненберг 1664 0.5 л.",250.99);

        createProduct(categoryRepository.findByName("Другие"), "Чай",
                "Черный/Зеленый/Фруктовый 1 л.",250.00);

        createProduct(categoryRepository.findByName("Другие"), "Лимонад",
                "Груша/Лимон/Фейхоа",170.99);
    }
    @Test
    void createClientObjects(){
        createClient(1L,"Кабисов Валерий Юрьевич","+7(978)657-45-67",
                "г.Владикавказ,ул.Генерала Плиева,д.115");
        createClient(2L,"Пупкина Алевтина Леопольдовна","+7(978)794-32-64",
                "г.Севастополь,ул.Пушкина,д.194");
        createClient(2L,"Зубенко Михаил Петрович","+7(978)536-09-45",
                "г.Севастополь,ул.Терновая,д.89");
    }

    @Test
    void createOrderProductObjects(){

    Optional<ClientOrder> co1 = clientOrderRepository.findById(1L);
    Optional<Product> product1 = productRepository.findById(2L);
    if(co1.isPresent() && product1.isPresent()) {
        createOrderProduct(co1.get(), product1.get(), 2L);
    }

    Optional<ClientOrder> co2 = clientOrderRepository.findById(2L);
    Optional<Product> product2 = productRepository.findById(13L);
    if(co2.isPresent() && product2.isPresent()){
        createOrderProduct(co2.get(),product2.get(),1L);
    }

        Optional<ClientOrder> co3 = clientOrderRepository.findById(3L);
        Optional<Product> product3 = productRepository.findById(17L);
        if(co3.isPresent() && product3.isPresent()){
            createOrderProduct(co3.get(),product3.get(),1L);
        }
    }

    @Test
    void createClientOrderObjects(){
    createClientOrder(clientRepository.findByFullName("Кабисов Валерий Юрьевич"),1,1150.00);
    createClientOrder(clientRepository.findByFullName("Зубенко Михаил Петрович"),2,1000.99);
    createClientOrder(clientRepository.findByFullName("Пупкина Алевтина Леопольдовна"),3,650.99);

    }
}

