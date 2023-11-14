import subprocess
import os

def clear_terminal():
    if os.name == 'nt':
        os.system('cls')
    else:
        os.system('clear')

def run_docker_compose_command(command, service_name=None):
    cmd = ["docker-compose", command]
    if service_name:
        cmd.append(service_name)
    result = subprocess.run(cmd, capture_output=True, text=True)
    if result.stderr:
        print("Error:", result.stderr)
    else:
        print(result.stdout)

def show_containers():
    print("Contenedores disponibles:")
    run_docker_compose_command("ps")

def service_menu():
    services = {
        "1": "db",
        "2": "backend-laravel",
        "3": "springboot",
        "4": "frontend-vue"
    }

    print("\nSelecciona un servicio:")
    for key, value in services.items():
        print(f"{key}. {value}")

    choice = input("Elige un servicio (o presiona Enter para todos): ")
    return services.get(choice, "")

def main_menu():
    while True:
        clear_terminal()
        show_containers()
        print("\n--- Menú Principal ---")
        print("1. Ver estado de los contenedores")
        print("2. Iniciar un servicio")
        print("3. Detener un servicio")
        print("4. Reiniciar un servicio")
        print("5. Salir")
        choice = input("Elige una opción: ")

        if choice == "1":
            run_docker_compose_command("ps")
        elif choice in ["2", "3", "4"]:
            clear_terminal()
            service_name = service_menu()
            if choice == "2":
                run_docker_compose_command("start", service_name)
            elif choice == "3":
                run_docker_compose_command("stop", service_name)
            elif choice == "4":
                run_docker_compose_command("restart", service_name)
        elif choice == "5":
            print("Saliendo...")
            break
        else:
            print("Opción no válida. Por favor, elige una opción del menú.")

if __name__ == "__main__":
    main_menu()
