FROM php:8.2-fpm-alpine

# Instalar dependencias necesarias para Alpine Linux
RUN apk add --no-cache \
    linux-headers \
    g++ \
    make \
    autoconf \
    curl \
    curl-dev

# Instalar extensiones de PHP
RUN docker-php-ext-install pdo pdo_mysql sockets \
    && apk add --no-cache curl-dev \
    && docker-php-ext-install curl

# Instalar Composer
RUN curl -sS https://getcomposer.org/installer | php -- \
    --install-dir=/usr/local/bin --filename=composer

# Copiar Composer desde la imagen de Composer
#COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

# Establecer directorio de trabajo
WORKDIR /app/laravel

# Copiar el proyecto Laravel y el script wait-for-it.sh
COPY ./laravel .
COPY ./db/wait-for-it.sh .

# Dar permisos de ejecución al script
RUN chmod +x wait-for-it.sh

# Instalar dependencias de Composer
RUN composer install

# Exponer el puerto 4000
EXPOSE 4000

