"use client";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { makeApiRequest } from "@/lib/helper";
import { FormEventHandler, useState } from "react";
import toast from "react-hot-toast";
import GridSolver from "./grid";


type ApiResponse = {
  message: string,
  path: string[],
  runtime: number;
  counter: number;
}

export default function Home() {
  const [algorithm, setAlgorithm] = useState("ucs");
  const [startWord, setStartWord] = useState("");
  const [endWord, setEndWord] = useState("");
  const [path, setPath] = useState<string[]>([]);
  const [runtime, setRuntime] = useState(0);
  const [count, setCount] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const onSubmit: FormEventHandler<HTMLFormElement> = async (e) => {
    e.preventDefault();

    if (!startWord || !endWord || startWord.length !== endWord.length || !algorithm) {
      if (!startWord) toast.error("Start word is required");
      if (!endWord) toast.error("End word is required");
      if (startWord.length !== endWord.length) toast.error("Start word and end word must have the same length");
      if (!algorithm) toast.error("Algorithm is required");
      return;
    }

    try {
      setIsLoading(true);
      const singleUrl = "/algorithm?method=" + algorithm
      await makeApiRequest({
        method: "POST",
        endpoint: singleUrl,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          startWord,
          endWord,
        }),
        loadingMessage:
          "Finding solution using " +
          algorithm +
          " algorithm...",
        successMessage: "Process completed successfully!",
        onSuccess: async (data: ApiResponse) => {
          const result = await data;
          setCount(result.counter);
          setPath(result.path);
          setRuntime(result.runtime);
        }
      })

      // Handle additional logic based on the response if needed
    } catch (err) {
      console.error(err);
      const errMsg =
        err instanceof Error ? err.message : "Something went wrong";
      toast.error(errMsg);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <main className="min-h-screen px-8 lg:px-24 py-14 w-full relative z-10 p-8 md:p-10 lg:p-16 xl:p-20 2xl:p-24 flex flex-col items-center justify-center gap-10 lg:gap-12">
      {/* Video background */}
      <video
        className="absolute inset-0 w-full h-full object-cover -z-10"
        autoPlay
        loop
        muted
      >
        <source src={"bg3.mp4"} type="video/mp4" />
      </video>

      {/* Title Section */}
      <section className="space-y-3 lg:space-y-5">
        <h1 className="text-center text-3xl md:text-4xl lg:text-5xl font-bold text-theme-5">Word Ladder Solver</h1>
        <h2 className="text-center text-2xl md:text-3xl lg:text-4xl font-bold text-theme-5">This solver using 3 algorithm as option</h2>
      </section>

      <form className="flex flex-col gap-7 lg:gap-10 items-center" onSubmit={onSubmit}>
        {/* Input */}
        <div className="flex gap-6 lg:gap-14 flex-col lg:flex-row">
          {/* Start words input  */}
          <div className="space-y-1.5 lg:space-y-3">
            <h3 className="text-center text-theme-4 font-medium text-xl lg:text-2xl">Start Words</h3>
            <Input type="text" placeholder="Start word" value={startWord} onChange={(e) => setStartWord(e.target.value)} />
          </div>
          {/* End word input */}
          <div className="space-y-1.5 lg:space-y-3">
            <h3 className="text-center text-theme-4 font-medium text-xl lg:text-2xl">End Words</h3>
            <Input type="text" placeholder="End word" value={endWord} onChange={(e) => setEndWord(e.target.value)} />
          </div>
        </div>

        {/* Radio Algorithm */}
        <div className="space-y-5">
          <h3 className="text-center text-theme-4 text-xl lg:text-2xl font-medium">Select Algorithm</h3>
          <RadioGroup className="flex lg:items-center max-md:flex-col justify-center gap-4 lg:gap-10" defaultValue="ucs" onValueChange={(selected) => {
            setAlgorithm(selected);
          }}>
            {/* UCS */}
            <div className="flex items-center space-x-2">
              <RadioGroupItem value="ucs" id="ucs" />
              <Label htmlFor="ucs">Uniform Cost Seach</Label>
            </div>
            {/* GBFS */}
            <div className="flex items-center space-x-2">
              <RadioGroupItem value="greedy" id="greedy" />
              <Label htmlFor="greedy">Greedy Best First Search</Label>
            </div>
            {/* Astar */}
            <div className="flex items-center space-x-2">
              <RadioGroupItem value="astar" id="astar" />
              <Label htmlFor="astar">A*</Label>
            </div>
          </RadioGroup>
        </div>

        {/* Submit */}
        <Button type="submit" variant="default" size="default">Solve</Button>
      </form>

      {/* Result */}
      <section>
        <h4 className="text-white">{runtime}</h4>
        <GridSolver path={path} />
      </section>
    </main>
  );
}
