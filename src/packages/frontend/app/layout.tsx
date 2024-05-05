import type { Metadata } from "next";
import { Poppins } from "next/font/google";
import { Toaster } from "react-hot-toast";
import "./globals.css";

const poppins = Poppins({
  subsets: ["latin"],
  weight: ["100", "200", "300", "400", "500", "600", "700", "800", "900"],
  display: "swap",
});

export const metadata: Metadata = {
  title: "Word Ladder Solver",
  description: "Unlock Word Paths: A web application designed to solve word ladder puzzles by uncovering connections between words using three powerful search algorithms - Uniform Cost Search, Greedy Best First Search, and A*. Dive into the world of word exploration and discover efficient solutions to challenging puzzles.",
  keywords: [
    "word ladder solver",
    "word ladder puzzles",
    "search algorithms",
    "Uniform Cost Search",
    "Greedy Best First Search",
    "A* algorithm",
    "word exploration",
    "puzzle solver",
  ],
};


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={poppins.className}>
        <Toaster />
        {children}
      </body>
    </html>
  );
}
