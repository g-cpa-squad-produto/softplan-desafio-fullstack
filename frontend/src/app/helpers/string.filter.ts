export const chainString = (text: String): string => {
  let string = text;
  const whiteListHex 	= {
        a : /[\xE0-\xE6]/g,
        e : /[\xE8-\xEB]/g,
        i : /[\xEC-\xEF]/g,
        o : /[\xF2-\xF6]/g,
        u : /[\xF9-\xFC]/g,
        c : /\xE7/g,
        n : /\xF1/g,
        _ : / |W|\./g
    };

  for (const character in whiteListHex) {
     if (whiteListHex.hasOwnProperty(character)) {
         const characterEr = whiteListHex[character];
         string = string.replace(characterEr, character);
     }
  }

  return string.toLowerCase();
};
