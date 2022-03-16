# Heading level 1
## Heading level 2
### Heading level 3
#### Heading level 4
##### Heading level 5
###### Heading level 6


Heading level 1
====
Heading level 2
----

This is a paragraph.

The quick brown fox jumps over the lazy dog near the river.

---
I just love **bold text**.
I just love __bold text__.
Love **is** bold

---
Italicized text is the *cat's meow*.
Italicized text is the _cat's meow_.
A*cat*meow

---
This text is ***really important***.
This text is ___really important___.
This text is __*really important*__.
This text is **_really important_**.
This is really***very***important text.

---
> Dorothy followed her through many of the beautiful rooms in her castle.
> 
> This is a block quote
> 
>> This is a nested block quote
>>
>>> > > Another one

---
> #### The quarterly results look great!
>
> - Revenue was off the chart.
> - Profits were higher than ever.
>
>  *Everything* is going according to **plan**.
>  

---
1. First item
2. Second item
3. Third item
4. Fourth item

---
1. First item
2. Second item
3. Third item
    1. Indented item
    2. Indented item
4. Fourth item

---
- First item of unordered list
- Second item
- Third item
- Fourth item

---
* First item
* Second item
* Third item
* Fourth item

---
+ First item
+ Second item
+ Third item
+ Fourth item

---
- First item
- Second item
- Third item
    - Indented item
    - Indented item
- Fourth item

---
* This is the first list item.
* Here's the second list item.

    I need to add another paragraph below the second list item.

* And here's the third list item.

---
* This is the first list item.
* Here's the second list item.

    > A blockquote would look great below the second list item.

* And here's the third list item.

---
1. Code Blocks
2. Open the file.
2. Find the following code block on line 21:

        <html>
          <head>
            <title>Test</title>
          </head>

3. Update the title to match the name of your website.

---
```html
<a> Another code block </a>
```

This is a code block too `next = next;`

``Use `code` in your Markdown file.``

---
1. Open the file containing the Linux mascot.
2. Marvel at its beauty.

    ![Tux, the Linux mascot](/assets/images/tux.png)

3. Close the file.



Try to put a blank line before...

---

...and after a horizontal rule.



My favorite search engine is [Duck Duck Go](https://duckduckgo.com).

My favorite search engine is [Duck Duck Go](https://duckduckgo.com "The best search engine for privacy").


<https://www.markdownguide.org>
<fake@example.com>


I love supporting the **[EFF](https://eff.org)**.
This is the *[Markdown Guide](https://www.markdownguide.org)*.
See the section on [`code`](#code).

[hobbit-hole][1]
[hobbit-hole] [1]

[1]: https://en.wikipedia.org/wiki/Hobbit#Lifestyle
[1]: https://en.wikipedia.org/wiki/Hobbit#Lifestyle "Hobbit lifestyles"
[1]: https://en.wikipedia.org/wiki/Hobbit#Lifestyle "Hobbit lifestyles"
[1]: https://en.wikipedia.org/wiki/Hobbit#Lifestyle "Hobbit lifestyles"
[1]: <https://en.wikipedia.org/wiki/Hobbit#Lifestyle> "Hobbit lifestyles"
[1]: <https://en.wikipedia.org/wiki/Hobbit#Lifestyle> "Hobbit lifestyles"
[1]: <https://en.wikipedia.org/wiki/Hobbit#Lifestyle> "Hobbit lifestyles"


In a hole in the ground there lived a hobbit. Not a nasty, dirty, wet hole, filled with the ends
of worms and an oozy smell, nor yet a dry, bare, sandy hole with nothing in it to sit down on or to
eat: it was a [hobbit-hole](https://en.wikipedia.org/wiki/Hobbit#Lifestyle "Hobbit lifestyles"), and that means comfort.


In a hole in the ground there lived a hobbit. Not a nasty, dirty, wet hole, filled with the ends
of worms and an oozy smell, nor yet a dry, bare, sandy hole with nothing in it to sit down on or to
eat: it was a [hobbit-hole][1], and that means comfort.

[1]: <https://en.wikipedia.org/wiki/Hobbit#Lifestyle> "Hobbit lifestyles"




![The San Juan Mountains are beautiful!](/assets/images/san-juan-mountains.jpg "San Juan Mountains")



[![An old rock in the desert](/assets/images/shiprock.jpg "Shiprock, New Mexico by Beau Rogers")](https://www.flickr.com/photos/beaurogers/31833779864/in/photolist-Qv3rFw-34mt9F-a9Cmfy-5Ha3Zi-9msKdv-o3hgjr-hWpUte-4WMsJ1-KUQ8N-deshUb-vssBD-6CQci6-8AFCiD-zsJWT-nNfsgB-dPDwZJ-bn9JGn-5HtSXY-6CUhAL-a4UTXB-ugPum-KUPSo-fBLNm-6CUmpy-4WMsc9-8a7D3T-83KJev-6CQ2bK-nNusHJ-a78rQH-nw3NvT-7aq2qf-8wwBso-3nNceh-ugSKP-4mh4kh-bbeeqH-a7biME-q3PtTf-brFpgb-cg38zw-bXMZc-nJPELD-f58Lmo-bXMYG-bz8AAi-bxNtNT-bXMYi-bXMY6-bXMYv)


---
\* Without the backslash, this would be a bullet in an unordered list.

| Character | Name |
|-----------|------|
| \ 		|	backslash 				|
|\` 		|	backtick (see also escaping backticks in code) |
|\* 		| 	asterisk 				|
| _ 		| 	underscore 				|
| { }		|	curly braces 			|
|[ ] 		|	brackets				|
| < > 		|	angle brackets 			|
| ( ) 		|	parentheses 			|
| \#		| pound sign 				|
|\+ 		|	plus sign				|
|\- 		|	minus sign (hyphen) 	|
|\. 		|	dot 				|
|\! 		|	exclamation mark 		|
|\|			|	pipe (see also escaping pipe in tables)|


# Math

https://en.wikibooks.org/wiki/LaTeX/Mathematics

#### Symbols

$$
-b \pm \sqrt{b^2 - 4ac} \over 2a
$$

$$
x = a_0 + \frac{1}{a_1 + \frac{1}{a_2 + \frac{1}{a_3 + a_4}}}
$$

$$
\forall x \in X, \quad \exists y \leq \epsilon
$$


#### Greek

$$
\alpha, \Alpha, \beta, \Beta, \gamma, \Gamma, \pi, \Pi, \phi, \varphi, \mu, \Phi
$$

#### Operators

$$
\cos (2\theta) = \cos^2 \theta - \sin^2 \theta
$$

$$
\lim\limits_{x \to \infty} \exp(-x) = 0
$$

$$
a \bmod b
$$

$$
x \equiv a \pmod{b}
$$


#### Power
$$
k_{n+1} = n^2 + k_n^2 - k_{n-1}
$$

$$
n^{22}
$$

$$
f(n) = n^5 + 4n^2 + 2 |_{n=17}
$$

#### Fractions and Binomials

$$
\frac{n!}{k!(n-k)!} = \binom{n}{k}
$$

$$
\frac{\frac{1}{x}+\frac{1}{y}}{y-z}
$$

$$
^3/_7
$$

$$
$x^\frac{1}{2}$ % no error
$$

$$
\begin{equation}
  x = a_0 + \cfrac{1}{a_1 
          + \cfrac{1}{a_2 
          + \cfrac{1}{a_3 + \cfrac{1}{a_4} } } }
\end{equation}
$$

$$
\begin{equation}
\frac{
    \begin{array}[b]{r}
      \left( x_1 x_2 \right)\\
      \times \left( x'_1 x'_2 \right)
    \end{array}
  }{
    \left( y_1y_2y_3y_4 \right)
  }
\end{equation}
$$

#### Roots

$$
\sqrt{\frac{a}{b}}
$$

$$
\sqrt[n]{1+x+x^2+x^3+\dots+x^n}
$$

$$
% New definition of square root:
% it renames \sqrt as \oldsqrt
\let\oldsqrt\sqrt
% it defines the new \sqrt in terms of the old one
\def\sqrt{\mathpalette\DHLhksqrt}
\def\DHLhksqrt#1#2{%
\setbox0=\hbox{$#1\oldsqrt{#2\,}$}\dimen0=\ht0
\advance\dimen0-0.2\ht0
\setbox2=\hbox{\vrule height\ht0 depth -\dimen0}%
{\box0\lower0.4pt\box2}}
$$

$$
\usepackage{letltxmacro}
\makeatletter
\let\oldr@@t\r@@t
\def\r@@t#1#2{%
\setbox0=\hbox{$\oldr@@t#1{#2\,}$}\dimen0=\ht0
\advance\dimen0-0.2\ht0
\setbox2=\hbox{\vrule height\ht0 depth -\dimen0}%
{\box0\lower0.4pt\box2}}
\LetLtxMacro{\oldsqrt}{\sqrt}
\renewcommand*{\sqrt}[2][\ ]{\oldsqrt[#1]{#2} }
\makeatother


$\sqrt[a]{b} \quad \oldsqrt[a]{b}$
$$

#### Sums and integrals

$$
n^{22}
$$

