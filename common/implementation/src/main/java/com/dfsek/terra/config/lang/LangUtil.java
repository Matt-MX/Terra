/*
 * This file is part of Terra.
 *
 * Terra is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Terra is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Terra.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.dfsek.terra.config.lang;

import java.io.File;
import java.io.IOException;

import com.dfsek.terra.api.Platform;
import com.dfsek.terra.api.entity.CommandSender;
import com.dfsek.terra.api.lang.Language;
import com.dfsek.terra.api.util.Logger;


public final class LangUtil {
    private static Language language;
    
    public static void load(String langID, Platform platform) {
        Logger logger = platform.logger();
        File file = new File(platform.getDataFolder(), "lang");
        try {
            File file1 = new File(file, langID + ".yml");
            logger.info(file1.getAbsolutePath());
            language = new LanguageImpl(file1);
            logger.info("Loaded language " + langID);
        } catch(IOException e) {
            logger.severe("Unable to load language: " + langID);
            e.printStackTrace();
            logger.severe("Double-check your configuration before reporting this to Terra!");
        }
    }
    
    public static Language getLanguage() {
        return language;
    }
    
    public static void send(String messageID, CommandSender sender, String... args) {
        language.getMessage(messageID).send(sender, args);
    }
}
