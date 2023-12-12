def generate_env_file():
    # Pedir al usuario los valores de las variables
    mysql_root_password = input("Ingrese MYSQL_ROOT_PASSWORD: ")
    mysql_user = input("Ingrese MYSQL_USER: ")
    mysql_password = input("Ingrese MYSQL_PASSWORD: ")

    port_vue = input("Ingrese PORT_VUE: ")
    port_laravel = input("Ingrese PORT_LARAVEL: ")
    port_springboot = input("Ingrese PORT_SPRINGBOOT: ")
    port_mysql = input("Ingrese PORT_MYSQL: ")

    ip_db = input("Ingrese IP_DB: ")
    ip_laravel = input("Ingrese IP_LARAVEL: ")
    ip_springboot = input("Ingrese IP_SPRINGBOOT: ")
    ip_vue = input("Ingrese IP_VUE: ")
    ip_gateway = input("Ingrese IP_GATEWAY: ")
    ip_subnet = input("Ingrese IP_SUBNET: ")

    network_name = input("Ingrese NETWORK_NAME: ")

    # Generar los nombres de los contenedores
    db_container = f"{network_name}DB"
    laravel_container = f"{network_name}LARAVEL"
    spring_container = f"{network_name}SPRING"
    vue_container = f"{network_name}VUE"

    # Crear el contenido del archivo .env
    env_content = (
        f"MYSQL_ROOT_PASSWORD={mysql_root_password}\n"
        f"MYSQL_USER={mysql_user}\n"
        f"MYSQL_PASSWORD={mysql_password}\n\n"
        f"PORT_VUE={port_vue}\n"
        f"PORT_LARAVEL={port_laravel}\n"
        f"PORT_SPRINGBOOT={port_springboot}\n"
        f"PORT_MYSQL={port_mysql}\n\n"
        f"IP_DB={ip_db}\n"
        f"IP_LARAVEL={ip_laravel}\n"
        f"IP_SPRINGBOOT={ip_springboot}\n"
        f"IP_VUE={ip_vue}\n"
        f"IP_GATEWAY={ip_gateway}\n"
        f"IP_SUBNET={ip_subnet}\n\n"
        f"NETWORK_NAME={network_name}\n"
        f"DB_CONTAINER={db_container}\n"
        f"LARAVEL_CONTAINER={laravel_container}\n"
        f"SPRING_CONTAINER={spring_container}\n"
        f"VUE_CONTAINER={vue_container}\n"
    )

    # Guardar el contenido en un archivo .env
    with open(".env", "w") as file:
        file.write(env_content)

    print("Archivo .env generado con éxito.")

# Para ejecutar el script
if __name__ == "__main__":
    generate_env_file()
