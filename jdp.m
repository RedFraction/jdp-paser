x = [0, 5, 10, 30, 108, 110, 112, 120, 135, 150, 180];
y = [0, 30,  40,  50,  40,  30,  27,  20,  10,  5,  0 ];
x2 = [0, 5, 105, 106, 108, 110, 112, 120, 135, 150, 180];
y2 = [0, -20, -20, -20, -20, -20, -20, -20, -10,  -5, 0 ];


function writeArray(file, arrXY)
    % Выводим кол-во строк
    fwrite(file, [length(arrXY), 0x00, 0x00, 0x00], 'uint8');

    % Выводим кол-во символов в координате и саму координату
    for i = 1:length(arrXY)
        element = arrXY(i);
        st = int2str(element);
        fwrite(file, [length(st), 0x00, 0x00, 0x00], "uint8");
        for sym = 1:length(st)
            fwrite(file, st(sym));
        end
    end
end

function writeCoords(file, x,y)
    if length(x) ~= length(y)
        error("Массивы X и Y должны быть одноого размера.");
    end

    % Поочередно выводим массивы
    writeArray(file, x);
    writeArray(file, y);

end

function exportJDP(x1, y1, x2, y2, name)
    % Создаем/Открываем файл
    fileID = fopen([name, '.jdp'], 'w');

    % Проверяем возможность работы с файлом
    if fileID == -1
        error('Невозмонжо открыть/создать файл');
    end

    % Заголовок файла
    fwrite(fileID, [0x72, 0xFE, 0x12, 0x01], 'uint8');

    % Выводим длину заголовка + заголовок
    fwrite(fileID, [uint8(length(name)), 0x00, 0x00, 0x00, name], 'uint8');

    writeCoords(fileID,x1,y1)
    writeCoords(fileID,x2,y2)

    % EOF (Конец файла)
    fwrite(fileID, [0x00, 0x00, 0x00, 0x00], 'uint8');

    % Закрываем файл
    fclose(fileID);
end

exportJDP(x, y, x2, y2, 'test-export-pena');
